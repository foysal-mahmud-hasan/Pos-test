package com.wst.wst_pos

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback, ZXingScannerView.ResultHandler {

    private val CAMERA_PERMISSION_CODE = 1001
    private var cameraPermissionGranted = false
    private var surfaceCreated = false
    private lateinit var scannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scannerView = ZXingScannerView(this)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            cameraPermissionGranted = true
            startScanning()
        } else {
            requestCameraPermission()
        }
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        // Handle the result here, for example, you can display the text from the QR code
        val text = rawResult?.text
        Toast.makeText(this, "QR Code Text: $text", Toast.LENGTH_LONG).show()

        // Resume scanning after a short delay to allow the user to read the text
        Handler(Looper.getMainLooper()).postDelayed({ scannerView.resumeCameraPreview(this) }, 2000)
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            AlertDialog.Builder(this)
                .setTitle("Camera permission needed")
                .setMessage("This app needs the Camera permission, please accept to use the QR Code scanner")
                .setPositiveButton("OK") { _, _ ->
                    // Request permission
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        }
    }

    private fun startScanning() {
        // Set the callback for the scanner view
        scannerView.setResultHandler(this)

        // Set the scanner view as the content view
        setContentView(scannerView)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    cameraPermissionGranted = true

                    if (surfaceCreated) {
                        startScanning()
                    }
                } else {
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        surfaceCreated = true

        if (cameraPermissionGranted) {
            startScanning()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        surfaceCreated = false
    }
}
package com.wst.wst_pos

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wst.wst_pos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 1500
    private lateinit var binding : ActivityMainBinding
    private lateinit var handler: Handler



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val users = listOf(Users("Test1", "123"),
        Users("Test2", "1234"))
        var userExist : Users? = null
        handler = Handler()
        binding.mainLayout.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.mainLayout.windowToken, 0)
                true
            } else {
                false
            }
        }
        binding.btnLogin.setOnClickListener {
            if(binding.etUsername.text.toString().isEmpty() || binding.etUserpassword.text.toString().isEmpty()){
                Toast.makeText(this, "Please input username/password", Toast.LENGTH_SHORT).show()
            }else{
                for (user in users){
                    if(user.userName == binding.etUsername.text.toString() && user.password == binding.etUserpassword.text.toString()){
                        binding.contentLayout.visibility = View.GONE
                        binding.loadingAnimation.visibility = View.VISIBLE
                        handler.postDelayed({
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }, SPLASH_DELAY)
                        break
                    } else{
                        Toast.makeText(this, "Wrong username/password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}




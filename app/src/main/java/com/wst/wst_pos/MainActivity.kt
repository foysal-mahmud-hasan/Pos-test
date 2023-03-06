package com.wst.wst_pos

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wst.wst_pos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val users = listOf<Users>()
        users.
        binding.btnLogin.setOnClickListener {
            binding.contentLayout.visibility = View.GONE
            binding.loadingAnimation.visibility = View.VISIBLE
        }
    }
}




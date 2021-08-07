package com.danieljayarajan.xmlrecipeapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.danieljayarajan.xmlrecipeapp.R
import com.danieljayarajan.xmlrecipeapp.databinding.ActivitySplashScreenBinding
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity(){
    private var binding: ActivitySplashScreenBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBindData()
        setupUI()
    }


    private fun onBindData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        binding?.lifecycleOwner = this
    }

    private fun setupUI() {
        ivSplashScreen.alpha = 0f
        ivSplashScreen.animate().setDuration(2000).alpha(1f).withEndAction{
            val intent = Intent(this, RecipeTypeBaseActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
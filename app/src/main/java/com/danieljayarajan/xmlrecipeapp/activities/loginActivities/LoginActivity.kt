package com.danieljayarajan.xmlrecipeapp.activities.loginActivities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.danieljayarajan.xmlrecipeapp.Navigator
import com.danieljayarajan.xmlrecipeapp.R
import com.danieljayarajan.xmlrecipeapp.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.tvSignUp

class LoginActivity : AppCompatActivity() {
    private val navigator = Navigator()

    private var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBindData()
        setupUI()
    }

    private fun onBindData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding?.lifecycleOwner = this
    }

    private fun setupUI() {
        btnLogin.setOnClickListener {
            if(!tilLoginEmail.text.isNullOrEmpty() && !tilLoginPassword.text.isNullOrEmpty())
                navigator.navigateToRecipeTypeActivity(this@LoginActivity)
            else
                Toast.makeText(applicationContext,"Please fill in the details properly", Toast.LENGTH_SHORT).show()
        }

        tvSignUp.setOnClickListener {
            navigator.navigateToSignUpActivity(this@LoginActivity)
        }
    }

    companion object {
        fun getCallingIntent(context: Context?): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
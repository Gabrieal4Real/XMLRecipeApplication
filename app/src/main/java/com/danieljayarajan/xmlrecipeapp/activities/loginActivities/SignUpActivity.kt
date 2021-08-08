package com.danieljayarajan.xmlrecipeapp.activities.loginActivities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.danieljayarajan.xmlrecipeapp.Navigator
import com.danieljayarajan.xmlrecipeapp.R
import com.danieljayarajan.xmlrecipeapp.databinding.ActivitySignUpBinding
import com.danieljayarajan.xmlrecipeapp.models.User
import com.danieljayarajan.xmlrecipeapp.utils.SharedPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.tvLogin

class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null
    private val navigator = Navigator()
    private val user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBindData()
        setupUI()
    }

    private fun onBindData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding?.lifecycleOwner = this
    }

    private fun setupUI() {
        btnSignUp.setOnClickListener {
            if (!tilSignUpEmail.text.isNullOrEmpty() && !tilSignUpPassword.text.isNullOrEmpty() && !tilSignUpConfirmPassword.text.isNullOrEmpty()) {
                if (tilSignUpPassword.text.toString() == tilSignUpConfirmPassword.text.toString()) {
                    user.email = tilSignUpEmail.text.toString()
                    user.password = tilSignUpPassword.text.toString()
                    SharedPrefsUtils.saveUser(user)
                    navigator.navigateToRecipeTypeActivity(this@SignUpActivity)
                } else
                    Toast.makeText(applicationContext, "Please make sure to confirm the password", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(applicationContext, "Please fill in the details properly", Toast.LENGTH_SHORT).show()

        }

        tvLogin.setOnClickListener {
            navigator.navigateToLoginActivity(this@SignUpActivity)
        }
    }

    companion object {
        fun getCallingIntent(context: Context?): Intent {
            val intent = Intent(context, SignUpActivity::class.java)

            return intent
        }
    }
}
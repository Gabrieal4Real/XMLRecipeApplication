package com.danieljayarajan.xmlrecipeapp

import android.content.Context
import android.content.Intent
import com.danieljayarajan.xmlrecipeapp.activities.loginActivities.LoginActivity
import com.danieljayarajan.xmlrecipeapp.activities.loginActivities.SignUpActivity
import com.danieljayarajan.xmlrecipeapp.activities.recipeActivities.DetailedRecipeTypeActivity
import com.danieljayarajan.xmlrecipeapp.activities.recipeActivities.ListViewRecipeTypeActivity
import com.danieljayarajan.xmlrecipeapp.activities.recipeActivities.RecipeTypeBaseActivity

class Navigator {
    fun navigateToRecipeTypeActivity(context: Context) {
        val intent = RecipeTypeBaseActivity.getCallingIntent(context)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun navigateToLoginActivity(context: Context) {
        val intent = LoginActivity.getCallingIntent(context)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun navigateToSignUpActivity(context: Context) {
        val intent = SignUpActivity.getCallingIntent(context)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun navigateToDetailedRecipeTypeActivity(
        context: Context,
        recipeList: HashMap<String, String>
    ) {
        val intent = DetailedRecipeTypeActivity.getCallingIntent(context, recipeList)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun navigateToListViewRecipeTypeActivity(
        context: Context,
        recipeList: ArrayList<HashMap<String, String>>
    ) {
        val intent = ListViewRecipeTypeActivity.getCallingIntent(context, recipeList)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}
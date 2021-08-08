package com.danieljayarajan.xmlrecipeapp

import android.content.Context
import android.content.Intent
import com.danieljayarajan.xmlrecipeapp.activities.DetailedRecipeTypeActivity
import com.danieljayarajan.xmlrecipeapp.activities.ListViewRecipeTypeActivity
import com.danieljayarajan.xmlrecipeapp.activities.RecipeTypeBaseActivity

class Navigator {
    fun navigateToRecipeTypeActivity(context: Context) {
        val intent = RecipeTypeBaseActivity.getCallingIntent(context)
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
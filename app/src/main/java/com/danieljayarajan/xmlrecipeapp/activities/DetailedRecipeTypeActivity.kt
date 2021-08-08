package com.danieljayarajan.xmlrecipeapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.danieljayarajan.xmlrecipeapp.R
import com.danieljayarajan.xmlrecipeapp.databinding.ActivityDetailedRecipeTypesBinding
import kotlinx.android.synthetic.main.activity_detailed_recipe_types.*

class DetailedRecipeTypeActivity: AppCompatActivity() {

    private var recipeParams: HashMap<String, String>? = null
    private var binding: ActivityDetailedRecipeTypesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onGetInputData()
        onBindData()
        setupUI()

    }

    private fun onBindData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailed_recipe_types)
        binding?.lifecycleOwner = this
    }

    private fun onGetInputData() {
        val recipeList = intent.getSerializableExtra("recipeList")
        if (recipeList != null) {
            recipeParams = recipeList as HashMap<String, String>?
        }
    }

    private fun setupUI() {
        setupGlide()
        setupText()
        setupFAB()
    }

    private fun setupFAB() {
        fab.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setupText() {
        tvFoodName.text = recipeParams?.get("subname")
        tvIngredientDescriptions.text = recipeParams?.get("ingredients")
        tvDirectionDescriptions.text = recipeParams?.get("directions")
    }

    private fun setupGlide() {
        Glide.with(this).load(recipeParams?.get("imageURL")).into(ivRecipeImage)
    }

    companion object {
        fun getCallingIntent(context: Context?, recipeList: HashMap<String, String>): Intent {
            val intent = Intent(context, DetailedRecipeTypeActivity::class.java)
            intent.putExtra("recipeList", recipeList)
            return intent
        }
    }
}
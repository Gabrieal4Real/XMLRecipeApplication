package com.danieljayarajan.xmlrecipeapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.danieljayarajan.xmlrecipeapp.R
import com.danieljayarajan.xmlrecipeapp.databinding.ActivityDetailedRecipeTypesBinding
import kotlinx.android.synthetic.main.activity_detailed_recipe_types.*

class DetailedRecipeTypeActivity: AppCompatActivity() {

    private var recipeParams: HashMap<String, String>? = null
    private var binding: ActivityDetailedRecipeTypesBinding?= null
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
        var recipeList = intent.getSerializableExtra("recipeList")
        if (recipeList != null) {
            recipeParams = recipeList as HashMap<String, String>?
        }
    }

    private fun setupUI() {
        setupGlide()
        tvFoodName.text = recipeParams?.get("subname")
        tvIngredientDescriptions.text = recipeParams?.get("ingredients")
    }

    private fun setupGlide() {
        Glide.with(this).load(recipeParams?.get("imageURL")).into(ivRecipeImage)
    }

}
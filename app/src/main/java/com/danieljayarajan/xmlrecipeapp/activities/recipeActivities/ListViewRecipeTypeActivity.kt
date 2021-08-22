package com.danieljayarajan.xmlrecipeapp.activities.recipeActivities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.danieljayarajan.xmlrecipeapp.helpers.Navigator
import com.danieljayarajan.xmlrecipeapp.R
import com.danieljayarajan.xmlrecipeapp.databinding.ActivityListViewRecipeTypeBinding
import kotlinx.android.synthetic.main.activity_list_view_recipe_type.*

class ListViewRecipeTypeActivity : AppCompatActivity() {

    private var recipeParams: ArrayList<HashMap<String, String>> = ArrayList()
    private var binding: ActivityListViewRecipeTypeBinding? = null
    private val navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onGetInputData()
        onBindData()
        setupUI()
    }

    private fun setupUI() {
        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = SimpleAdapter(
            this,
            recipeParams,
            R.layout.item_custom_list,
            arrayOf("name", "subname"),
            intArrayOf(R.id.tvName, R.id.tvSubName)
        )
        lvRecipeList.adapter = adapter
        lvRecipeList.setOnItemClickListener { _, _, position, _ ->
            navigator.navigateToDetailedRecipeTypeActivity(baseContext, recipeParams[position])
        }
    }

    private fun onBindData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_view_recipe_type)
        binding?.lifecycleOwner = this
    }

    private fun onGetInputData() {
        val recipeList = intent.getSerializableExtra("recipeList")
        if (recipeList != null) {
            recipeParams = recipeList as ArrayList<HashMap<String, String>>
        }
    }

    companion object {
        fun getCallingIntent(
            context: Context?,
            recipeList: ArrayList<HashMap<String, String>>
        ): Intent {
            val intent = Intent(context, ListViewRecipeTypeActivity::class.java)
            intent.putExtra("recipeList", recipeList)
            return intent
        }
    }
}
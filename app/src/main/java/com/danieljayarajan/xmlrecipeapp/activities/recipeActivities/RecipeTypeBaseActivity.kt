package com.danieljayarajan.xmlrecipeapp.activities.recipeActivities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.danieljayarajan.xmlrecipeapp.Navigator
import com.danieljayarajan.xmlrecipeapp.R
import com.danieljayarajan.xmlrecipeapp.databinding.ActivityBaseRecipeTypeBinding
import kotlinx.android.synthetic.main.activity_base_recipe_type.*
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.SAXException
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException


open class RecipeTypeBaseActivity : AppCompatActivity() {

    private var recipeDataHashMap = HashMap<String, String>()
    private var recipeList: ArrayList<HashMap<String, String>> = ArrayList()
    private val languages = arrayListOf<String>()

    private var binding: ActivityBaseRecipeTypeBinding? = null
    private val navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBindData()
        setupUI()
    }

    private fun onBindData() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base_recipe_type)
        binding?.lifecycleOwner = this
    }

    private fun setupUI() {
        setupHashMap()
        setupSpinner()
        setupButton()
    }

    private fun setupButton() {
        btnAllRecipes.setOnClickListener {
            navigator.navigateToListViewRecipeTypeActivity(baseContext, recipeList)
        }
    }

    private fun setupHashMap() {
        try {
            val xmlData = assets.open("recipetypes.xml")
            val builderFactory = DocumentBuilderFactory.newInstance()

            val docBuilder = builderFactory.newDocumentBuilder()
            val doc = docBuilder.parse(xmlData)
            val nList = doc.getElementsByTagName("recipetype")

            for (i in 0 until nList.length) {
                if (nList.item(0).nodeType == Node.ELEMENT_NODE) {
                    recipeDataHashMap = HashMap()
                    val element = nList.item(i) as Element
                    recipeDataHashMap["name"] = getNodeValue("name", element)
                    recipeDataHashMap["subname"] = getNodeValue("subname", element)
                    recipeDataHashMap["imageURL"] = getNodeValue("imageURL", element)
                    recipeDataHashMap["ingredients"] = getNodeValue("ingredients", element)
                    recipeDataHashMap["directions"] = getNodeValue("directions", element)

                    recipeDataHashMap["name"]?.let { languages.add(it) }
                    recipeList.add(recipeDataHashMap)
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        }
    }

    private fun setupSpinner() {
        if (tvDropDownSpinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
            tvDropDownSpinner.setAdapter(adapter)

            tvDropDownSpinner.onItemClickListener = OnItemClickListener { parent, view, position, rowId ->
                navigator.navigateToDetailedRecipeTypeActivity(baseContext, recipeList[position])
                }
        }
    }

    private fun getNodeValue(tag: String, element: Element): String {
        val nodeList = element.getElementsByTagName(tag)
        val node = nodeList.item(0)
        if (node != null) {
            if (node.hasChildNodes()) {
                val child = node.firstChild
                while (child != null) {
                    if (child.nodeType === Node.TEXT_NODE) {
                        return child.nodeValue
                    }
                }
            }
        }
        return ""
    }

    companion object {
        fun getCallingIntent(context: Context?): Intent {
            val intent = Intent(context, RecipeTypeBaseActivity::class.java)
            return intent
        }
    }
}
package com.danieljayarajan.xmlrecipeapp.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.SAXException
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import android.content.Intent
import com.danieljayarajan.xmlrecipeapp.R
import com.danieljayarajan.xmlrecipeapp.databinding.ActivityBaseRecipeTypeBinding
import kotlinx.android.synthetic.main.activity_base_recipe_type.*

open class RecipeTypeBaseActivity : AppCompatActivity() {
    private var recipeDataHashMap = HashMap<String, String>()
    private var recipeList: ArrayList<HashMap<String, String>> = ArrayList()
    private val languages = arrayListOf<String>()

    private var binding: ActivityBaseRecipeTypeBinding?= null

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
    }

    private fun setupHashMap() {
        try {
            val xmlData = assets.open("recipetypes.xml")
            val builderFactory = DocumentBuilderFactory.newInstance()

            val docBuilder = builderFactory.newDocumentBuilder()
            val doc = docBuilder.parse(xmlData)
            val nList = doc.getElementsByTagName("recipetype")

            languages.add("Select One Of These Recipe Types")

            for (i in 0 until nList.length) {
                if (nList.item(0).nodeType == Node.ELEMENT_NODE) {
                    recipeDataHashMap = HashMap()
                    val element = nList.item(i) as Element
                    recipeDataHashMap["name"] = getNodeValue("name", element)
                    recipeDataHashMap["subname"] = getNodeValue("subname", element)
                    recipeDataHashMap["imageURL"] = getNodeValue("imageURL", element)
                    recipeDataHashMap["ingredients"] = getNodeValue("ingredients", element)

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
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if(position != 0)
                    {
                        val intent = Intent(baseContext, DetailedRecipeTypeActivity::class.java)
                        intent.putExtra("recipeList", recipeList[position-1])
                        startActivity(intent)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
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

}
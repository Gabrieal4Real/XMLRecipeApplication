package com.danieljayarajan.xmlrecipeapp.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.danieljayarajan.xmlrecipeapp.models.User
import com.google.gson.Gson

object SharedPrefsUtils {
    private val APP_PREFS = "RECIPE_PREFS"
    lateinit var sp: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    private val gson = Gson()
    private var isInitialized: Boolean = false

    fun initSharedUtils(context: Context) {
        if (isInitialized)
            return
        sp = context.getSharedPreferences(APP_PREFS, Activity.MODE_PRIVATE)
        editor = sp.edit()
        isInitialized = true
    }

    fun getSavedString(prefId: String): String {
        return sp.getString(prefId, "") ?: ""
    }

    fun saveUser(user: User) {
        val json: String = gson.toJson(user)
        editor.putString(user.email, json)
        editor.commit()
    }

    fun getUser(userEmail: String): User? {
        val jsonUser = getSavedString(userEmail)
        return gson.fromJson(jsonUser, User::class.java)
    }
}
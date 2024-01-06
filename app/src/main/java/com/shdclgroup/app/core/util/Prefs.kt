package com.shdclgroup.app.core.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shdclgroup.app.features.login.data.model.LoginModel

class Prefs (context: Context) {
    private val SHARED_PREF_NAME = "BSK-HALL"
    val preferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    val gson: Gson = GsonBuilder().create()

    init {

    }


    var loginModel: LoginModel
        get() = get<LoginModel>(::loginModel.name) ?: LoginModel()
        set(value) = put(::loginModel.name, value)




    fun <T> put(key: String, `object`: T) {
        val jsonString = gson.toJson(`object`)
        preferences.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> get(key: String): T? {
        val value = preferences.getString(key, null)
        return if(value != null) gson.fromJson(value, T::class.java) else null
    }

}
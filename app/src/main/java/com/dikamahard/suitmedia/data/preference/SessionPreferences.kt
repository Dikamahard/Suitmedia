package com.dikamahard.suitmedia.data.preference

import android.content.Context

class SessionPreferences(context: Context) {

    companion object {
        const val PREFS_NAME = "pref"
        const val NAME = "name"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveName(name: String) {
        val editor = preferences.edit()
        editor.putString(NAME, name).apply()
    }

    fun getUserData(key: String): String? {
        return preferences.getString(key, null)
    }
}
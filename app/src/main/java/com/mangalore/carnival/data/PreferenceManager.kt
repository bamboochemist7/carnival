package com.mangalore.carnival.data


import android.content.Context
import androidx.annotation.NonNull


object PreferenceManager {

    @JvmStatic
    fun get(context: Context, key: String): String? {
        val sharedPrefs = context.getSharedPreferences("mc-app",
                Context.MODE_PRIVATE)
        return sharedPrefs.getString(key, "-1")
    }

    @JvmStatic
    fun put(context: Context, key: String, value: Long) {
        val sharedPrefs = context.getSharedPreferences("mc-app",
                Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putLong(key, value)
        editor.apply()
    }
    @JvmStatic
    fun getBoolean(context: Context, key: String): Boolean? {
        val sharedPrefs = context.getSharedPreferences("mc-app",
                Context.MODE_PRIVATE)
       return sharedPrefs.getBoolean(key,true)
    }
    @JvmStatic
    fun putBoolean(context: Context, key: String, value: Boolean) {
        val sharedPrefs = context.getSharedPreferences("mc-app",
                Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }
    @JvmStatic
    fun put(context: Context, key: String, value: String) {
        val sharedPrefs = context.getSharedPreferences("mc-app",
                Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString(key, value)
        editor.apply()
    }
    @JvmStatic
    fun getLong(context: Context, key: String): Long {
        val sharedPrefs = context.getSharedPreferences("mc-app",
                Context.MODE_PRIVATE)
        return sharedPrefs.getLong(key, 0)
    }

    @JvmStatic
    fun getInt(context: Context, key: String): Int {
        val sharedPrefs = context.getSharedPreferences("mc-app",
                Context.MODE_PRIVATE)
        return sharedPrefs.getInt(key, 0)
    }


    @JvmStatic
    fun put(context: Context, key: String, value: Int) {
        val sharedPrefs = context.getSharedPreferences("mc-app",
                Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    @JvmStatic
    fun Clear(context: Context) {
        val sharedPrefs = context.getSharedPreferences("mc-app",
                Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
    }

}

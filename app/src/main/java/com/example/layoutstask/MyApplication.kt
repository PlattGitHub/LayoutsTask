package com.example.layoutstask

import android.app.Application
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate

/**
 * Application class that is used to set Default Night mode before all Activities being created.
 *
 * @author Alexander Gorin
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        if (sharedPreferences.getBoolean(PREFS_AUTO_KEY, true)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO)
        } else {
            if (sharedPreferences.getBoolean(PREFS_MODE_KEY, false)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
package com.example.layoutstask

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

/**
 * Settings activity that holds [SettingsFragment] PreferenceFragmentCompat subclass.
 *
 * @author Alexander Gorin
 */
class SettingsActivity : AppCompatActivity() {

    private val listener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences: SharedPreferences, key: String ->
            val mode = when (key) {
                PREFS_AUTO_KEY -> {
                    if (sharedPreferences.getBoolean(key, false)) {
                        AppCompatDelegate.MODE_NIGHT_AUTO
                    } else {
                        if (sharedPreferences.getBoolean(PREFS_MODE_KEY, false)) {
                            AppCompatDelegate.MODE_NIGHT_YES
                        } else {
                            AppCompatDelegate.MODE_NIGHT_NO
                        }
                    }
                }
                PREFS_MODE_KEY -> {
                    if (sharedPreferences.getBoolean(key, false)) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_NO
                    }
                }
                else -> return@OnSharedPreferenceChangeListener
            }
            AppCompatDelegate.setDefaultNightMode(mode)
            recreate()
        }

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)
        }
    }
}

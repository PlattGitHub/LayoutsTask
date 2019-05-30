package com.example.layoutstask.ui

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.layoutstask.*
import com.example.layoutstask.model.Flight
import com.example.layoutstask.utils.DataGenerator
import com.example.layoutstask.utils.OnThemeChangeListener
import com.example.layoutstask.utils.PREFS_AUTO_KEY
import com.example.layoutstask.utils.PREFS_MODE_KEY
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Class-container for 2 fragments with 2 buttons.
 * First [Button] shows the fragment based on ConstraintLayout.
 * Second [Button] shows the fragment based on LinearLayout.
 *
 * @author Alexander Gorin
 */
class MainActivity : AppCompatActivity(), OnThemeChangeListener {

    private val departData: Flight by lazy { DataGenerator.generateDepartData() }
    private val returnData: Flight by lazy { DataGenerator.generateReturnData() }
    private val sharedPreferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(
                FragmentConstraint.newInstance(
                    departData,
                    returnData
                )
            )
        }

        buttonFragment1.setOnClickListener {
            replaceFragment(
                FragmentConstraint.newInstance(
                    departData,
                    returnData
                )
            )
        }

        buttonFragment2.setOnClickListener {
            replaceFragment(
                FragmentNotConstraint.newInstance(
                    departData,
                    returnData
                )
            )
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.settings_action -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onThemeChanged() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPreferences.edit().putBoolean(PREFS_AUTO_KEY, false).apply()
                sharedPreferences.edit().putBoolean(PREFS_MODE_KEY, true).apply()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPreferences.edit().putBoolean(PREFS_MODE_KEY, false).apply()
            }
        }
        recreate()
    }
}

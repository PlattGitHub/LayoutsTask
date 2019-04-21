package com.example.layoutstask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Class-container for 2 fragments with 2 buttons.
 * First [Button] shows the fragment based on ConstraintLayout.
 * Second [Button] shows the fragment based on LinearLayout.
 *
 * @author Alexander Gorin
 */
class MainActivity : AppCompatActivity() {

    private val departData: Flight by lazy { DataGenerator.generateDepartData() }
    private val returnData: Flight by lazy { DataGenerator.generateReturnData() }

    private var constraintFragmentToSet = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState != null) {
            constraintFragmentToSet = savedInstanceState.getBoolean(KEY_LAYOUT)
        }

        replaceFragment(
            if (constraintFragmentToSet) {
                FragmentConstraint.newInstance(departData, returnData)
            } else {
                FragmentNotConstraint.newInstance(departData, returnData)
            }
        )

        buttonFragment1.setOnClickListener {
            replaceFragment(
                FragmentConstraint.newInstance(departData, returnData)
            )
        }

        buttonFragment2.setOnClickListener {
            replaceFragment(
                FragmentNotConstraint.newInstance(departData, returnData)
            )
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        constraintFragmentToSet = fragment is FragmentConstraint
        outState?.putBoolean(KEY_LAYOUT, constraintFragmentToSet)
    }

    private companion object {
        const val KEY_LAYOUT = "KEY_LAYOUT"
    }
}

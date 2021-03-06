package com.example.layoutstask.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.layoutstask.model.Flight
import com.example.layoutstask.utils.OnThemeChangeListener
import com.example.layoutstask.R
import kotlinx.android.synthetic.main.fragment_constraint.view.*

/**
 * A simple [Fragment] subclass based on ConstraintLayout.
 *
 * @author Alexander Gorin
 */
class FragmentConstraint : Fragment() {

    var onThemeChangeListener: OnThemeChangeListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnThemeChangeListener) {
            onThemeChangeListener = context
        } else {
            throw RuntimeException("${context.toString()} must implement OnThemeChangeListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_constraint, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Flight>(DEPART_DATA)?.apply {
            with(view) {
                textViewDepartDate.text = date
                textViewDepartFreeSeats.text =
                    String.format(getString(R.string.free_seats), freeSeats)
                textViewDepartPrice.text =
                    String.format(getString(R.string.price_currency), price, currency)
                textViewDepartTakeOffCity.text = takeOffCity
                textViewDepartLandCity.text = landCity
                textViewDepartTakeOffTime.text = takeOffTime
                textViewDepartLandTime.text = landTime
                textViewDepartDuration.text = duration
            }

        }

        arguments?.getParcelable<Flight>(RETURN_DATA)?.apply {
            with(view) {
                textViewReturnDate.text = date
                textViewReturnFreeSeats.text =
                    String.format(getString(R.string.free_seats), freeSeats)
                textViewReturnPrice.text =
                    String.format(getString(R.string.price_currency), price, currency)
                textViewReturnTakeOffCity.text = takeOffCity
                textViewReturnLandCity.text = landCity
                textViewReturnTakeOffTime.text = takeOffTime
                textViewReturnLandTime.text = landTime
                textViewReturnDuration.text = duration
            }
        }

        view.fab.setOnClickListener {
            onThemeChangeListener?.onThemeChanged()
        }
    }

    companion object {
        fun newInstance(departData: Flight, returnData: Flight) = FragmentConstraint().apply {
            arguments = Bundle().apply {
                putParcelable(DEPART_DATA, departData)
                putParcelable(RETURN_DATA, returnData)
            }
        }

        const val DEPART_DATA = "DEPART_DATA"
        const val RETURN_DATA = "RETURN_DATA"
    }
}

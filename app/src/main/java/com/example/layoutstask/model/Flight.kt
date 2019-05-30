package com.example.layoutstask.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Simple data class for flight information
 * with parcelable implementation.
 *
 * @author Alexander Gorin
 */
@Parcelize
data class Flight(
    val date: String,
    val freeSeats: Int,
    val price: Int,
    val currency: String,
    val takeOffCity: String,
    val landCity: String,
    val takeOffTime: String,
    val landTime: String,
    val duration: String
) : Parcelable
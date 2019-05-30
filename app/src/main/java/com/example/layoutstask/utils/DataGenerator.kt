package com.example.layoutstask.utils

import com.example.layoutstask.model.Flight

/**
 * Singleton for generating flight information.
 *
 * @author Alexander Gorin
 */
object DataGenerator {
    fun generateDepartData() = Flight(
        "16 SEP 2019",
        3,
        435, "BYN",
        "MSQ",
        "FLO",
        "00:20",
        "09:20",
        "9:00"
    )

    fun generateReturnData() = Flight(
        "17 SEP 2019",
        5,
        488,
        "BYN",
        "FLO",
        "MSQ",
        "05:10",
        "09:20",
        "4:10"
    )
}
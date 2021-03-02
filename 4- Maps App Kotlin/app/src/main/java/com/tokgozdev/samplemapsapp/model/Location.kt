package com.tokgozdev.samplemapsapp.model

import java.io.Serializable

data class Location(

        var name: String,
        var country: String,
        var type: String,
        var latitude: Double,
        var longitude: Double

) : Serializable
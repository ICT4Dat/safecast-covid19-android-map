package at.ict4d.covid19map.server.models

import com.google.gson.annotations.SerializedName

const val SERIALIZED_POST_POSITION_LONGITUDE = "lon"
const val SERIALIZED_POST_POSITION_LATITUDE = "lat"

data class SerializedPostPosition(

    @SerializedName(SERIALIZED_POST_POSITION_LATITUDE)
    val latitude: Double,

    @SerializedName(SERIALIZED_POST_POSITION_LONGITUDE)
    val longitude: Double
)

package at.ict4d.covid19map.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.ZonedDateTime

const val SAFECAST_MAP_DATA_SET_TABLE_TABLE_NAME = "safecast_map_data_set"

@Parcelize
@Entity(
    tableName = SAFECAST_MAP_DATA_SET_TABLE_TABLE_NAME
)
data class SafecastMapDataSet(

    @PrimaryKey
    val id: Int,

    val url: String,

    val color: String,

    val title: String,

    val content: String,

    val slug: String,

    val locale: String,

    val latitude: Double,

    val longitude: Double,

    val created: ZonedDateTime,

    val dateOfTest: ZonedDateTime,

    val age: Int
) : Parcelable
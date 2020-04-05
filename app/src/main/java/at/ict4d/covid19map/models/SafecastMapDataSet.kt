package at.ict4d.covid19map.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import at.ict4d.covid19map.server.models.SerializedPost
import at.ict4d.covid19map.utils.URL_SAFE_CAST_POSTS
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime

const val SAFECAST_MAP_DATA_SET_TABLE_TABLE_NAME = "safecast_map_data_set"
const val SAFECAST_MAP_DATA_SET_TABLE_ID = "id"
const val SAFECAST_MAP_DATA_SET_TABLE_URL = "url_json"
const val SAFECAST_MAP_DATA_SET_TABLE_COLOR = "color"
const val SAFECAST_MAP_DATA_SET_TABLE_TITLE = "title"
const val SAFECAST_MAP_DATA_SET_TABLE_CONTENT = "content"
const val SAFECAST_MAP_DATA_SET_TABLE_SLUG = "slug"
const val SAFECAST_MAP_DATA_SET_TABLE_LOCALE = "locale"
const val SAFECAST_MAP_DATA_SET_TABLE_LATITUDE = "latitude"
const val SAFECAST_MAP_DATA_SET_TABLE_LONGITUDE = "longitude"
const val SAFECAST_MAP_DATA_SET_TABLE_CREATED = "created"
const val SAFECAST_MAP_DATA_SET_TABLE_DATE_OF_TEST = "date_of_test"
const val SAFECAST_MAP_DATA_SET_TABLE_AGE = "age"
const val SAFECAST_MAP_DATA_SET_MEASURES_TAKEN = "measures_taken"
const val SAFECAST_MAP_DATA_SET_URL_POSTS = "url_posts"

@Parcelize
@Entity(
    tableName = SAFECAST_MAP_DATA_SET_TABLE_TABLE_NAME
)
data class SafecastMapDataSet(

    @PrimaryKey
    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_ID)
    val id: Int,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_URL)
    val urlJson: String,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_COLOR)
    val color: String,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_TITLE)
    val title: String,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_CONTENT)
    val content: String,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_SLUG)
    val slug: String,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_LOCALE)
    val locale: String,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_LATITUDE)
    val latitude: Double,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_LONGITUDE)
    val longitude: Double,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_CREATED)
    val created: ZonedDateTime,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_DATE_OF_TEST)
    val dateOfTest: LocalDateTime,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_TABLE_AGE)
    val age: Int?,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_MEASURES_TAKEN)
    val measuresTaken: List<String>?,

    @ColumnInfo(name = SAFECAST_MAP_DATA_SET_URL_POSTS)
    val urlPost: String
) : Parcelable {

    @Ignore
    constructor(serializedPost: SerializedPost) : this(
        serializedPost.id,
        serializedPost.url,
        serializedPost.color,
        serializedPost.title,
        serializedPost.content,
        serializedPost.slug,
        serializedPost.locale,
        serializedPost.values.position.first().latitude,
        serializedPost.values.position.first().longitude,
        serializedPost.created,
        serializedPost.values.dateOfTest.first(),
        serializedPost.values.age?.firstOrNull(),
        serializedPost.values.measuresTaken,
        URL_SAFE_CAST_POSTS + serializedPost.id
    )
}

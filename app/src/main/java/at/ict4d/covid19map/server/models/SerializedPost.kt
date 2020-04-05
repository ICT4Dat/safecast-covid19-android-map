package at.ict4d.covid19map.server.models

import com.google.gson.annotations.SerializedName
import org.threeten.bp.ZonedDateTime

const val SERIALIZED_POSTS_ID = "id"
const val SERIALIZED_URL = "url"
const val SERIALIZED_COLOR = "color"
const val SERIALIZED_TITLE = "title"
const val SERIALIZED_CONTENT = "content"
const val SERIALIZED_SLUG = "slug"
const val SERIALIZED_LOCALE = "locale"
const val SERIALIZED_CREATED = "created"
const val SERIALIZED_VALUES = "values"

data class SerializedPost(

    @SerializedName(SERIALIZED_POSTS_ID)
    val id: Int,

    @SerializedName(SERIALIZED_URL)
    val url: String,

    @SerializedName(SERIALIZED_COLOR)
    val color: String,

    @SerializedName(SERIALIZED_TITLE)
    val title: String,

    @SerializedName(SERIALIZED_CONTENT)
    val content: String,

    @SerializedName(SERIALIZED_SLUG)
    val slug: String,

    @SerializedName(SERIALIZED_LOCALE)
    val locale: String,

    @SerializedName(SERIALIZED_CREATED)
    val created: ZonedDateTime,

    @SerializedName(SERIALIZED_VALUES)
    val values: SerializedPostValues
)

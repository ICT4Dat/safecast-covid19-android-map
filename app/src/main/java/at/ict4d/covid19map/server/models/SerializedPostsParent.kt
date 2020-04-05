package at.ict4d.covid19map.server.models

import com.google.gson.annotations.SerializedName

const val SERIALIZED_POSTS_PARENT_COUNT = "count"
const val SERIALIZED_POSTS_PARENT_POSTS = "results"
const val SERIALIZED_POSTS_PARENT_ORDER = "order"
const val SERIALIZED_POSTS_PARENT_ORDER_BY = "orderby"
const val SERIALIZED_POSTS_PARENT_CURRENT_URL = "curr"
const val SERIALIZED_POSTS_PARENT_NEXT_URL = "next"
const val SERIALIZED_POSTS_PARENT_PREVIOUS_URL = "prev"
const val SERIALIZED_POSTS_PARENT_TOTAL_COUNT = "total_count"

data class SerializedPostsParent(

    @SerializedName(SERIALIZED_POSTS_PARENT_COUNT)
    val count: Int,

    @SerializedName(SERIALIZED_POSTS_PARENT_POSTS)
    val posts: List<SerializedPost>,

    @SerializedName(SERIALIZED_POSTS_PARENT_ORDER)
    val order: String,

    @SerializedName(SERIALIZED_POSTS_PARENT_ORDER_BY)
    val orderBy: String,

    @SerializedName(SERIALIZED_POSTS_PARENT_CURRENT_URL)
    val currentUrl: String,

    @SerializedName(SERIALIZED_POSTS_PARENT_NEXT_URL)
    val nextUrl: String,

    @SerializedName(SERIALIZED_POSTS_PARENT_PREVIOUS_URL)
    val previousUrl: String,

    @SerializedName(SERIALIZED_POSTS_PARENT_TOTAL_COUNT)
    val totalCount: Int
)

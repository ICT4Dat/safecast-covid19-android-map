package at.ict4d.covid19map.server.api

import at.ict4d.covid19map.server.models.SerializedPostsParent
import retrofit2.Response
import retrofit2.http.GET

interface SafecastApi {

    @GET("/api/v3/posts")
    suspend fun getAllPosts(): Response<SerializedPostsParent>
}

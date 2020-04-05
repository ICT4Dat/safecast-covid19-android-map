package at.ict4d.covid19map.server.repository

import at.ict4d.covid19map.models.SafecastMapPost
import at.ict4d.covid19map.persistence.database.dao.SafecastMapPostDao
import at.ict4d.covid19map.server.api.SafecastApi
import at.ict4d.covid19map.server.resultFlow

class SafecastMapPostRepository(
    private val safecastMapPostDao: SafecastMapPostDao,
    private val safecastApi: SafecastApi
) {

    fun getSafecastMapPosts() = resultFlow(
        databaseQuery = { safecastMapPostDao.getAllSafecastMapPosts() },
        networkCall = { safecastApi.getAllPosts() },
        saveCallResult = { serializedParent ->
            safecastMapPostDao.insertAllSafecastMapPosts(serializedParent.posts.map {
                SafecastMapPost(
                    it
                )
            })
        }
    )
}

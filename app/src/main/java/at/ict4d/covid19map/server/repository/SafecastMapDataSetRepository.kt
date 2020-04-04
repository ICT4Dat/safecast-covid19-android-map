package at.ict4d.covid19map.server.repository

import at.ict4d.covid19map.models.SafecastMapDataSet
import at.ict4d.covid19map.persistence.database.dao.SafecastMapDataSetDao
import at.ict4d.covid19map.server.api.SafecastApi
import at.ict4d.covid19map.server.resultFlow

class SafecastMapDataSetRepository(
    private val safecastMapDataSetDao: SafecastMapDataSetDao,
    private val safecastApi: SafecastApi
) {

    fun getSafecastMapDataSets() = resultFlow(
        databaseQuery = { safecastMapDataSetDao.getAllSafecastMapDataSets() },
        networkCall = { safecastApi.getAllPosts() },
        saveCallResult = { serializedParent ->
            safecastMapDataSetDao.insertAllSafecastMapDataSets(serializedParent.posts.map {
                SafecastMapDataSet(
                    it
                )
            })
        }
    )
}

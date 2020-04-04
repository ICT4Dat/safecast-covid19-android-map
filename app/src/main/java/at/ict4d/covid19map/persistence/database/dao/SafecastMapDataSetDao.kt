package at.ict4d.covid19map.persistence.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import at.ict4d.covid19map.models.SAFECAST_MAP_DATA_SET_TABLE_TABLE_NAME
import at.ict4d.covid19map.models.SafecastMapDataSet
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SafecastMapDataSetDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAllSafecastMapDataSets(mapDataSets: List<SafecastMapDataSet>): List<Long>

    @Query("""
        SELECT *
        FROM $SAFECAST_MAP_DATA_SET_TABLE_TABLE_NAME
    """)
    abstract fun getAllSafecastMapDataSets(): Flow<List<SafecastMapDataSet>>
}

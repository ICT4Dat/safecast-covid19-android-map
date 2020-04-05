package at.ict4d.covid19map.persistence.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import at.ict4d.covid19map.models.SAFECAST_MAP_POST_TABLE_TABLE_NAME
import at.ict4d.covid19map.models.SafecastMapPost
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SafecastMapPostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAllSafecastMapPosts(mapPosts: List<SafecastMapPost>): List<Long>

    @Query(
        """
        SELECT *
        FROM $SAFECAST_MAP_POST_TABLE_TABLE_NAME
    """
    )
    abstract fun getAllSafecastMapPosts(): Flow<List<SafecastMapPost>>
}

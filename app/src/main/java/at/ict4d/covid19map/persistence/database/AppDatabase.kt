package at.ict4d.covid19map.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import at.ict4d.covid19map.models.SafecastMapDataSet
import at.ict4d.covid19map.persistence.database.dao.SafecastMapDataSetDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

const val DATABASE_NAME = "safecast_map_database"

@Database(
    version = 1,

    entities = [
        SafecastMapDataSet::class
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSafecastMapDataSetDao(): SafecastMapDataSetDao
}

class Converters : KoinComponent {

    private val gson: Gson by inject()

    @TypeConverter
    fun localDateFromString(value: String?): LocalDate? =
        if (value == null) null else LocalDate.parse(value)

    @TypeConverter
    fun localDateToString(date: LocalDate?): String? =
        date?.format(DateTimeFormatter.ISO_LOCAL_DATE)

    @TypeConverter
    fun zonedDateTimeFromString(value: String?): ZonedDateTime? =
        if (value == null) null else ZonedDateTime.parse(value)

    @TypeConverter
    fun zonedDateTimeToString(date: ZonedDateTime?): String? =
        date?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)

    @TypeConverter
    fun localDateFromTimeString(value: String?): LocalDateTime? =
        if (value == null) null else LocalDateTime.parse(value)

    @TypeConverter
    fun localDateTimeToString(date: LocalDateTime?): String? =
        date?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    @TypeConverter
    fun listFromString(string: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(string, listType)
    }

    @TypeConverter
    fun stringFromList(list: List<String>?): String? = gson.toJson(list)
}

package at.ict4d.covid19map.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import at.ict4d.covid19map.models.SafecastMapDataSet
import org.threeten.bp.LocalDate
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
abstract class AppDatabase : RoomDatabase()

class Converters {

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
}

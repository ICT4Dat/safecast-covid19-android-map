package at.ict4d.covid19map.server.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import java.lang.reflect.Type

class GsonLocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime? {
        try {
            if (json?.asString.isNullOrEmpty()) {
                return null
            }
            return LocalDateTime.parse(
                json?.asString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            )
        } catch (e: Exception) {
            Timber.w(e, "Caught Gson LocalDateTime Parse Exception with: $json")
        }
        return null
    }
}

class GsonZonedDateTimeDeserializer : JsonDeserializer<ZonedDateTime> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ZonedDateTime? {
        try {
            if (json?.asString.isNullOrEmpty()) {
                return null
            }
            return ZonedDateTime.parse(json?.asString)
        } catch (e: Exception) {
            Timber.w(e, "Caught Gson LocalDateTime Parse Exception with: $json")
        }
        return null
    }
}

class GsonZonedDateTimeSerializer : JsonSerializer<ZonedDateTime> {
    override fun serialize(
        src: ZonedDateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement? {
        src?.let {
            return JsonPrimitive(it.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
        }
        return null
    }
}

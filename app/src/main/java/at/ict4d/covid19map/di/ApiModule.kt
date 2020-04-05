package at.ict4d.covid19map.di

import at.ict4d.covid19map.App
import at.ict4d.covid19map.BuildConfig
import at.ict4d.covid19map.server.api.SafecastApi
import at.ict4d.covid19map.server.utils.GsonLocalDateTimeDeserializer
import at.ict4d.covid19map.server.utils.GsonZonedDateTimeDeserializer
import at.ict4d.covid19map.server.utils.GsonZonedDateTimeSerializer
import com.google.android.gms.security.ProviderInstaller
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.UUID
import java.util.concurrent.TimeUnit

val apiModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://covid19map.safecast.org/")
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .client(get<OkHttpClient>())
            .build()
            .create(SafecastApi::class.java)
    }

    factory {
        GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, GsonLocalDateTimeDeserializer())
            .registerTypeAdapter(ZonedDateTime::class.java, GsonZonedDateTimeDeserializer())
            .registerTypeAdapter(ZonedDateTime::class.java, GsonZonedDateTimeSerializer())
            .create()
    }

    single {
        val cacheDir = File(get<App>().cacheDir, UUID.randomUUID().toString())
        // 10 MiB cache
        val cache = Cache(cacheDir, 10 * 1024 * 1024)

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val builder = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)

        try {
            ProviderInstaller.installIfNeeded(get<App>())
        } catch (e: Exception) {
            Timber.e("SecurityException: Google Play Services not available.")
        }

        builder.build()
    }
}

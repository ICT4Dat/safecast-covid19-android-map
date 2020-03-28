package at.ict4d.covid19map

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import at.ict4d.covid19map.di.*
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        setUpTimber()

        // Start Koin
        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    viewModelModule,
                    apiModule,
                    repositoryModule,
                    roomModule
                )
            )
        }
    }

    private fun setUpTimber() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return String.format(
                    "C:%s: Line %s",
                    super.createStackElementTag(element),
                    element.lineNumber
                )
            }
        })
    }
}
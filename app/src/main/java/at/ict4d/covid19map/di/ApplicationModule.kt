package at.ict4d.covid19map.di

import at.ict4d.covid19map.App
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    single { androidApplication() as App }
}

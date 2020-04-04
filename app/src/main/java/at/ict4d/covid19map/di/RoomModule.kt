package at.ict4d.covid19map.di

import androidx.room.Room
import at.ict4d.covid19map.App
import at.ict4d.covid19map.persistence.database.AppDatabase
import at.ict4d.covid19map.persistence.database.DATABASE_NAME
import org.koin.dsl.module

val roomModule = module {

    single {
        Room.databaseBuilder(get<App>(), AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    single { get<AppDatabase>().getSafecastMapDataSetDao() }
}

package at.ict4d.covid19map.di

import at.ict4d.covid19map.server.repository.SafecastMapDataSetRepository
import org.koin.dsl.module

val repositoryModule = module {

    // Repositories
    single { SafecastMapDataSetRepository() }
}

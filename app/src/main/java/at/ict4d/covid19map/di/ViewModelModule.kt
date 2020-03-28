package at.ict4d.covid19map.di

import at.ict4d.covid19map.ui.tabs.map.MapContainerViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MapContainerViewModel(get()) }
}

package at.ict4d.covid19map.ui.tabs.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import at.ict4d.covid19map.server.repository.SafecastMapDataSetRepository
import kotlinx.coroutines.Dispatchers

class MapContainerViewModel(
    safecastMapDataSetRepository: SafecastMapDataSetRepository
) : ViewModel() {

    val mapData = safecastMapDataSetRepository.getSafecastMapDataSets().asLiveData(Dispatchers.IO)
}

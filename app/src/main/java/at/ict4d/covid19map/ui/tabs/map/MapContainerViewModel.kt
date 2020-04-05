package at.ict4d.covid19map.ui.tabs.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import at.ict4d.covid19map.server.repository.SafecastMapPostRepository
import kotlinx.coroutines.Dispatchers

class MapContainerViewModel(
    safecastMapPostRepository: SafecastMapPostRepository
) : ViewModel() {

    val mapData = safecastMapPostRepository.getSafecastMapPosts().asLiveData(Dispatchers.IO)

    var lastClickedMarkerTitle: String? = null
}

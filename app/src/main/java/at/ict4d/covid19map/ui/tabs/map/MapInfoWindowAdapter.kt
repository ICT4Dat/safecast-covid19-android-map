package at.ict4d.covid19map.ui.tabs.map

import android.app.Activity
import android.view.View
import androidx.databinding.DataBindingUtil
import at.ict4d.covid19map.R
import at.ict4d.covid19map.databinding.MapInfoWindowBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MapInfoWindowAdapter(
    private val activity: Activity
) : GoogleMap.InfoWindowAdapter {

    private val binding: MapInfoWindowBinding =
        DataBindingUtil.inflate(activity.layoutInflater, R.layout.map_info_window, null, false)

    override fun getInfoContents(marker: Marker?): View {
        return binding.root
    }

    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }
}

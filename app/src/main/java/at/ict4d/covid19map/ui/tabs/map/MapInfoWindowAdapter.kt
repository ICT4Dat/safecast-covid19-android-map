package at.ict4d.covid19map.ui.tabs.map

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.databinding.DataBindingUtil
import at.ict4d.covid19map.R
import at.ict4d.covid19map.databinding.MapInfoWindowBinding
import at.ict4d.covid19map.models.SafecastMapPost
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MapInfoWindowAdapter(
    activity: Activity
) : GoogleMap.InfoWindowAdapter {

    private val binding: MapInfoWindowBinding =
        DataBindingUtil.inflate(activity.layoutInflater, R.layout.map_info_window, null, false)

    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }

    override fun getInfoContents(marker: Marker?): View {
        (marker?.tag as? SafecastMapPost)?.let { data ->
            binding.title.text = data.title
            binding.content.text = data.content
            binding.colorStrip.setBackgroundColor(Color.parseColor(data.color))
        }

        return binding.root
    }
}

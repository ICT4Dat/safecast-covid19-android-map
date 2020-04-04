package at.ict4d.covid19map.ui.tabs.map

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import at.ict4d.covid19map.R
import at.ict4d.covid19map.databinding.MapContainerFragmentBinding
import at.ict4d.covid19map.ui.base.BaseFragment
import at.ict4d.covid19map.ui.tabs.TabContainerFragmentDirections
import at.ict4d.covid19map.utils.navigateSafe
import at.ict4d.covid19map.utils.setVisible
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import timber.log.Timber
import java.util.Locale

class MapContainerFragment : BaseFragment<MapContainerFragmentBinding, MapContainerViewModel>(
    R.layout.map_container_fragment,
    MapContainerViewModel::class
), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap

    private var days = emptyList<LocalDate>()

    private val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(
        Locale.getDefault()
    )

    companion object {

        @JvmStatic
        fun newInstance() = MapContainerFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.swipeToRefresh.isEnabled = false

        binding.seekBarDays.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                days.getOrNull(progress - 1)?.let { date ->
                    binding.dateSeekBarTv.text = date.format(formatter)
                    // set new date
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    days.getOrNull(seekBar.progress - 1)?.let { date ->
                        binding.dateSeekBarTv.text = date.format(formatter)
                        binding.dateSeekBarTv.setVisible(true)
                        binding.dateSmallTv.setVisible(false)
                    }
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                binding.dateSeekBarTv.setVisible(false)
                binding.dateSmallTv.setVisible(true)
            }
        })

        // TODO: Remove this line and replace with real data to display the Seekbar with dates
        binding.daysLoaded = true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.setInfoWindowAdapter(
            MapInfoWindowAdapter(
                requireActivity()
            )
        )

        model.mapData.observe(viewLifecycleOwner) { resource ->

            handleApiResponse(resource, swipeRefreshLayout = binding.swipeToRefresh)

            // *******************
            // TODO: continue here
            // *******************

            Timber.d("Resource: $resource")
        }

//        model.mapDataSet.observe(viewLifecycleOwner) { resource ->
//
//            handleApiResponse(resource, swipeRefreshLayout = binding.swipeToRefresh)
//
//            map.clear()
//            map.setOnMarkerClickListener(this)
//
//            var marker: Marker
//            resource.data?.forEach { data ->
//                if (data.confirmed > 0 || data.deaths > 0 || data.recovered > 0) {
//                    marker = map.addMarker(
//                        MarkerOptions()
//                            .position(LatLng(data.latitude, data.longitude))
//                            .title(data.countryName)
//                    )
//                    marker.tag = data
//
//                    if (model.lastClickedMarkerTitle != null && data.countryName == model.lastClickedMarkerTitle) {
//                        marker.showInfoWindow()
//                    }
//                }
//            }
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_map_container_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_settings -> {
            findNavController().navigateSafe(
                R.id.tabContainerFragment,
                TabContainerFragmentDirections
                    .actionTabContainerFragmentToSettingsContainerFragment()
            )
            true
        }
        R.id.menu_refresh -> {
            // trigger refresh
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        Timber.d("clicked on marker")
        return false
    }
}

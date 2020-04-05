package at.ict4d.covid19map.ui.tabs.list

import at.ict4d.covid19map.R
import at.ict4d.covid19map.databinding.MapPostListFragmentBinding
import at.ict4d.covid19map.ui.base.BaseFragment

class MapPostListFragment : BaseFragment<MapPostListFragmentBinding, MapPostListViewModel>(
    R.layout.map_post_list_fragment,
    MapPostListViewModel::class
) {
    companion object {

        @JvmStatic
        fun newInstance() = MapPostListFragment()
    }
}

package at.ict4d.covid19map.ui.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import at.ict4d.covid19map.ui.tabs.map.MapContainerFragment

class TabContainerFragmentStateAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MapContainerFragment.newInstance()
            1 -> MapContainerFragment.newInstance()
            else -> throw IllegalArgumentException("position tab not valid")
        }
    }
}

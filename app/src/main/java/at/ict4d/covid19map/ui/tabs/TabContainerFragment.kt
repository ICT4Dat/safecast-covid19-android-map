package at.ict4d.covid19map.ui.tabs

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import at.ict4d.covid19map.R
import at.ict4d.covid19map.databinding.TabContainerFragmentBinding
import at.ict4d.covid19map.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class TabContainerFragment : BaseFragment<TabContainerFragmentBinding, TabContainerViewModel>(
    R.layout.tab_container_fragment,
    TabContainerViewModel::class
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewpager.adapter =
            TabContainerFragmentStateAdapter(childFragmentManager, lifecycle)

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.viewpager.isUserInputEnabled = position != 0
            }
        })

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewpager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_map)
                1 -> tab.text = getString(R.string.tab_list)
            }
        }.attach()
    }
}

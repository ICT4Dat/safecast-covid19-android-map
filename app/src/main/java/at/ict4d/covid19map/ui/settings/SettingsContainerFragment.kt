package at.ict4d.covid19map.ui.settings

import at.ict4d.covid19map.R
import at.ict4d.covid19map.databinding.SettingsContainerFragmentBinding
import at.ict4d.covid19map.ui.base.BaseFragment

class SettingsContainerFragment :
    BaseFragment<SettingsContainerFragmentBinding, SettingsContainerViewModel>(
        R.layout.settings_container_fragment,
        SettingsContainerViewModel::class
    )

package at.ict4d.covid19map.ui.settings

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import at.ict4d.covid19map.BuildConfig
import at.ict4d.covid19map.R
import at.ict4d.covid19map.utils.browseCustomTab
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

class SettingsFragment : PreferenceFragmentCompat() {

    private var isOssActivityOpen = false
    private var versionPressedCounter = 0

    override fun onResume() {
        super.onResume()
        isOssActivityOpen = false
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        findPreference<Preference>(getString(R.string.app_version))?.summary =
            BuildConfig.VERSION_NAME
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return when (preference?.key) {
            getString(R.string.app_version) -> {

                versionPressedCounter += 1

                if (versionPressedCounter >= 7) {
                    versionPressedCounter = 0
                    Toast.makeText(
                        requireActivity(),
                        "Version Code: ${BuildConfig.VERSION_CODE}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                true
            }
            getString(R.string.software_licences) -> {
                if (!isOssActivityOpen) {
                    isOssActivityOpen = true
                    startActivity(Intent(requireActivity(), OssLicensesMenuActivity::class.java))
                    OssLicensesMenuActivity.setActivityTitle(getString(R.string.software_licences))
                }
                true
            }
            getString(R.string.data_source) -> {
                requireActivity().browseCustomTab("https://covid19map.safecast.org")
                true
            }
            getString(R.string.icon_design) -> {
                requireActivity().browseCustomTab("https://www.facebook.com/chloe.zimmermann1")
                true
            }
            getString(R.string.implemented_by) -> {
                requireActivity().browseCustomTab("https://www.ict4d.at")
                true
            }
            else -> super.onPreferenceTreeClick(preference)
        }
    }
}

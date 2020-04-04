package at.ict4d.covid19map.utils

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import at.ict4d.covid19map.R
import timber.log.Timber

fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun NavController.navigateSafe(
    @IdRes currentDestination: Int,
    action: NavDirections? = null,
    @IdRes destinationId: Int? = null,
    alternateCurrentDestinations: List<Int> = emptyList(),
    extras: FragmentNavigator.Extras? = null,
    navOptions: NavOptions? = null,
    args: Bundle? = null
) {
    this.currentDestination?.id?.let { id ->
        try {
            if (id == currentDestination || alternateCurrentDestinations.contains(id)) {
                if (action != null) {
                    if (extras != null) {
                        navigate(action, extras)
                    } else {
                        navigate(action)
                    }
                } else if (destinationId != null) {
                    navigate(destinationId, args, navOptions, extras)
                }
            }
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}

fun Context.browseCustomTab(url: String) {
    CustomTabsIntent
        .Builder()
        .setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
        .build()
        .launchUrl(this, Uri.parse(url))
}

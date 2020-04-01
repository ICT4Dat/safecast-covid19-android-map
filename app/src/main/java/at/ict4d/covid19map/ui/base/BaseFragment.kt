package at.ict4d.covid19map.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import at.ict4d.covid19map.R
import at.ict4d.covid19map.server.Resource
import at.ict4d.covid19map.server.Status
import at.ict4d.covid19map.utils.AutoClearedValue
import at.ict4d.covid19map.utils.setVisible
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.reflect.KClass

abstract class BaseFragment<B : ViewDataBinding, V : ViewModel>(
    @LayoutRes private val layoutID: Int,
    viewModelClass: KClass<V>
) : Fragment() {

    protected var binding by AutoClearedValue<B>()

    protected val model: V by viewModel(viewModelClass)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutID, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.root.findViewById<Toolbar>(R.id.toolbar)?.let { toolbar ->
            setUpToolbar(toolbar)
        }

        return binding.root
    }

    fun <T> handleApiResponse(
        resource: Resource<T>,
        swipeRefreshLayout: SwipeRefreshLayout? = null,
        progressbar: ProgressBar? = null,
        errorTextView: TextView? = null
    ) {

        swipeRefreshLayout?.isRefreshing = resource.status == Status.LOADING
        progressbar?.setVisible(resource.status == Status.LOADING)

        errorTextView?.setVisible((resource.status == Status.ERROR ||
            resource.status == Status.SUCCESS) && (resource.data == null ||
            (resource.data as? List<*>).isNullOrEmpty()))

        when (resource.status) {
            Status.SUCCESS ->
                Timber.d("${this::class.java.simpleName} repository update " +
                    "success with Status ${resource.status}")
            Status.ERROR -> Toast.makeText(
                requireContext(),
                R.string.update_error,
                Toast.LENGTH_LONG
            ).show()
            Status.LOADING ->
                Timber.d("${this::class.java.simpleName} repository " +
                    "loading with Status ${resource.status}")
        }
    }

    fun setUpToolbar(toolbar: Toolbar) {
        (activity as? AppCompatActivity)?.let {
            it.setSupportActionBar(toolbar)
            it.setupActionBarWithNavController(findNavController())
        }
    }

    fun setToolbarTitle(
        @StringRes titleRes: Int,
        @StringRes subTitleRes: Int? = null
    ) {
        setToolbarTitle(
            getString(titleRes),
            if (subTitleRes == null) null else getString(subTitleRes)
        )
    }

    fun setToolbarTitle(
        title: String,
        subTitle: String? = null
    ) {
        binding.root.findViewById<Toolbar>(R.id.toolbar)?.let { toolbar ->
            toolbar.title = title
            if (subTitle != null) {
                toolbar.subtitle = subTitle
            }
        }
    }
}

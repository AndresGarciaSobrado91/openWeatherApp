package com.garcia.openweatherapp.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.garcia.openweatherapp.R
import com.garcia.openweatherapp.databinding.FragmentWeatherBinding
import com.garcia.openweatherapp.domain.GetDefaultWeatherUseCaseResult
import com.garcia.openweatherapp.ui.base.BaseDaggerFragment
import com.garcia.openweatherapp.ui.viewModel.MainViewModel
import javax.inject.Inject

class WeatherFragment : BaseDaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel : MainViewModel
    lateinit var binding: FragmentWeatherBinding

    private val getWeatherObserver = Observer<GetDefaultWeatherUseCaseResult>{ result ->
        when(result){
            is GetDefaultWeatherUseCaseResult.Loading -> {
                showLoading(getString(R.string.loading))
            }
            is GetDefaultWeatherUseCaseResult.Result -> {
                if (result.weather != null){
                    viewModel.setWeather(result.weather)
                    setMainImage(result.weather.weather[0].icon)
                } else {
                    hideLoading()
                    showErrorDialog()
                }
            }
            is GetDefaultWeatherUseCaseResult.Error -> {
                hideLoading()
                showErrorDialog()
            }
        }
    }

    private fun showErrorDialog() {
        val myDialog = CustomDialogFragment()
        myDialog.isCancelable = false
        myDialog.show(
            childFragmentManager,CustomDialogFragment.TAG
        )
    }

    private fun setMainImage(icon: String) {
        val imageUrl = "https://openweathermap.org/img/wn/$icon@4x.png"
        Glide
            .with(this)
            .load(imageUrl)
            .centerCrop()
            .skipMemoryCache(true)
            .addListener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    hideLoading()
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    hideLoading()
                    return false
                }

            })
            .into(binding.imageView)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { args ->
            val args = WeatherFragmentArgs.fromBundle(args)
            viewModel.onViewCreated(args)
        }
        viewModel.registerObserver(viewLifecycleOwner,viewModel.weatherResult,getWeatherObserver)
    }

    override fun onDestroyView() {
        viewModel.removeObservers(this)
        super.onDestroyView()
    }
}
package com.garcia.openweatherapp.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.garcia.openweatherapp.R
import com.garcia.openweatherapp.databinding.FragmentHomeBinding
import com.garcia.openweatherapp.domain.model.Coord
import com.garcia.openweatherapp.ui.adapter.HomeUIAdapter
import com.garcia.openweatherapp.ui.adapter.IHomeAdapter
import com.garcia.openweatherapp.ui.base.BaseDaggerFragment
import com.garcia.openweatherapp.util.*
import com.garcia.openweatherapp.util.enum.LocationsEnum
import com.google.android.gms.location.LocationServices


class HomeFragment : BaseDaggerFragment(), IHomeAdapter {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewLocations.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            val mLayoutManager = LinearLayoutManager(context)
            layoutManager = mLayoutManager
            layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
            adapter = HomeUIAdapter(this@HomeFragment)
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onLocationClicked(item: LocationsEnum) {
        if (!item.equals(LocationsEnum.CURRENT_LOCATION))
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToWeatherFragment(
                    item.title,
                    null
                )
            )
        else
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            )
                requestLocation()
            else
                requestPermission()
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE_COARSE_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_COARSE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocation()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null)
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToWeatherFragment(
                    null,
                    Coord(location.latitude,location.longitude)
                )
            )
        }

        fusedLocationClient.lastLocation.addOnFailureListener {
            Log.d("LOCATION", "requestLocation: ${it.printStackTrace()}")
        }
    }

    companion object {
        const val REQUEST_CODE_COARSE_LOCATION = 100
    }
}
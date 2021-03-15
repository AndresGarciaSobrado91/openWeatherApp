package com.garcia.openweatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garcia.openweatherapp.databinding.LocationItemBinding
import com.garcia.openweatherapp.util.enum.LocationsEnum

class HomeUIAdapter(val callback: IHomeAdapter) : RecyclerView.Adapter<HomeUIAdapter.ViewHolderLocationRow>(){

    inner class ViewHolderLocationRow(val binding: LocationItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderLocationRow {
        val binding = LocationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolderLocationRow(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderLocationRow, position: Int) {
        val item = LocationsEnum.values()[position]
        with(holder.binding){
            textViewTitle.text = item.title
            imageViewLocation.setImageResource(item.drawableId)
            mainLayout.setOnClickListener {
                callback.onLocationClicked(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return LocationsEnum.values().size
    }
}

interface IHomeAdapter {
    fun onLocationClicked(item : LocationsEnum)
}
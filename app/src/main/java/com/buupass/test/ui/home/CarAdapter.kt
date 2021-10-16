package com.buupass.test.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buupass.test.data.Car
import com.buupass.test.databinding.GarageListItemBinding

class CarAdapter(private val context: Context): RecyclerView.Adapter<CarAdapter.CarHolder>() {

    var carList = mutableListOf<Car>()

    inner class CarHolder(binding: GarageListItemBinding): RecyclerView.ViewHolder(binding.root) {
        val time = binding.time
        val carPicture = binding.carPicture
        val carBrand = binding.vehicleBrand
        val amount = binding.amount
        val perTime = binding.perTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = GarageListItemBinding.inflate(layoutInflater, parent, false)
        return CarHolder(binding)
    }

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        val currentItem = carList[position]
        with(currentItem) {
            holder.time.text = time
            holder.carPicture.setImageResource(image)
            holder.carBrand.text = brand
            holder.amount.text = amount
            holder.perTime.text = perTime
        }
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    fun setItems(list: List<Car>) {
        carList.addAll(list)
        notifyDataSetChanged()
    }

}
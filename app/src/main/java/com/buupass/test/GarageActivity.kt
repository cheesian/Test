package com.buupass.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buupass.test.data.Car
import com.buupass.test.databinding.ActivityGarageBinding
import com.buupass.test.ui.home.CarAdapter

class GarageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGarageBinding
    private lateinit var backButton: CardView
    private lateinit var recyclerView: RecyclerView
    private lateinit var carAdapter: CarAdapter
    private var carList = mutableListOf<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGarageBinding.inflate(layoutInflater)
        backButton = binding.cardBack
        recyclerView = binding.recycler
        carAdapter = CarAdapter(this)
        generateList()
        recyclerView.layoutManager = GridLayoutManager(
            this,
            2
        )
        recyclerView.adapter = carAdapter
        carAdapter.setItems(carList)
        setContentView(binding.root)
    }

    private fun generateList() {
        val car1 = Car(
            time = "Daily",
            image = R.drawable.porsche,
            brand = "Porsche",
            amount = "AED 420",
            perTime = "Per day"
        )
        val car2 = Car(
            time = "Weekly",
            image = R.drawable.mustang,
            brand = "Mustang",
            amount = "AED 400",
            perTime = "Per week"
        )
        val car3 = Car(
            time = "Monthly",
            image = R.drawable.lambo,
            brand = "Lamborghini",
            amount = "AED 450",
            perTime = "Per month"
        )
        val car4 = Car(
            time = "Daily",
            image = R.drawable.porsche,
            brand = "Porsche",
            amount = "AED 420",
            perTime = "Per day"
        )
        val car5 = Car(
            time = "Weekly",
            image = R.drawable.mustang,
            brand = "Mustang",
            amount = "AED 400",
            perTime = "Per week"
        )
        val car6 = Car(
            time = "Monthly",
            image = R.drawable.lambo,
            brand = "Lamborghini",
            amount = "AED 450",
            perTime = "Per month"
        )
        carList.add(car1)
        carList.add(car2)
        carList.add(car3)
        carList.add(car4)
        carList.add(car5)
        carList.add(car6)
    }
}
package com.buupass.test.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buupass.test.GarageActivity
import com.buupass.test.R
import com.buupass.test.data.Car
import com.buupass.test.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var buttonPlus: Button
    private lateinit var availableCars: ConstraintLayout
    private lateinit var myGarage: TextView
    private lateinit var more: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var carAdapter: CarAdapter
    private var carList = mutableListOf<Car>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        buttonPlus = binding.plus
        availableCars = binding.constraintAvailableCars
        myGarage = binding.garage
        more = binding.more
        recyclerView = binding.recycler
        carAdapter = CarAdapter(requireContext())
        generateList()
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.adapter = carAdapter
        carAdapter.setItems(carList)

        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        setOnClicks()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setOnClicks() {
        buttonPlus.setOnClickListener {
            showGarage()
        }
        availableCars.setOnClickListener {
            showGarage()
        }
        myGarage.setOnClickListener {
            showGarage()
        }
        more.setOnClickListener {
            showGarage()
        }
    }

    private fun showGarage(){
        startActivity(Intent(requireContext(), GarageActivity::class.java))
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
        carList.add(car1)
        carList.add(car2)
        carList.add(car3)
    }
}
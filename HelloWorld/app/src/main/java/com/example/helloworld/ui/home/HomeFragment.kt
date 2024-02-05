package com.example.helloworld.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.helloworld.TankViewModel
import com.example.helloworld.TimeTemperaturePair
import com.example.helloworld.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val tankViewModel : TankViewModel by lazy {
        ViewModelProvider(requireActivity()).get(TankViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tankViewModel =
            ViewModelProvider(requireActivity()).get(TankViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tankName: TextView = binding.tankName
        tankViewModel.tankName.observe(viewLifecycleOwner) {
            tankName.text = it
        }

        val currentTempView: TextView = binding.currentTemp
        tankViewModel.currentTemp.observe(viewLifecycleOwner) { currentTemp : Double? ->
            currentTempView.text = if (currentTemp != null) String.format("%.1f\u2103", currentTemp) else "---"
        }

        val timeVsTempChart : TimeVsTempChart = binding.temperatureChart
        tankViewModel.temperatureData.observe(viewLifecycleOwner) {
                  timeVsTempChart.addEntries(it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
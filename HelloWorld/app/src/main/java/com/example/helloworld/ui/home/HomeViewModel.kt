package com.example.helloworld.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld.TimeTemperaturePair
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.Timer
import kotlin.concurrent.timerTask
import kotlin.random.Random

class HomeViewModel : ViewModel() {
    private val _tankName = MutableLiveData<String>().apply {
        value = "My Tank"
    }
    val tankName: LiveData<String> = _tankName;

    private val _currentTemp = MutableLiveData<Double>().apply {
        value = null
    }
    val currentTemp: LiveData<Double> = _currentTemp;
    private val _temperatureData = MutableLiveData<List<TimeTemperaturePair>>()
    val temperatureData: LiveData<List<TimeTemperaturePair>> = _temperatureData

    init {
        _temperatureData.value = emptyList()
    }

    fun generateRandomTemperature() {
        viewModelScope.launch {
            while (isActive) { // Keeps running until the ViewModel is cleared
                val randomTemperature = Random.nextDouble(20.0, 30.0) // Random temp between 20.0 and 30.0
                val currentTime = LocalDateTime.now()

                withContext(Dispatchers.Main) {
                    addTemperature(currentTime, randomTemperature)
                }

                delay(1000) // Wait for 1 second before generating the next value
            }
        }
    }
    fun addTemperature(datetime: LocalDateTime, temperature: Double) {
        val updatedList = _temperatureData.value.orEmpty().toMutableList()
        updatedList.add(TimeTemperaturePair(datetime, temperature))
        _temperatureData.value = updatedList
        _currentTemp.value = temperature;
    }

    fun getTemperatures(): LiveData<List<TimeTemperaturePair>> = temperatureData
}
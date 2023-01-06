package com.example.projekt.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekt.data.PlantRepository
import com.example.projekt.data.notification.Notification
import com.example.projekt.data.plant.Plant
import com.example.projekt.events.AddNotificationEvents
import com.example.projekt.events.AddPlantEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNotificationViewModel @Inject constructor(
    private val repository: PlantRepository,

): ViewModel() {

    var hour by mutableStateOf(0)
        private set

    var min by mutableStateOf(0)
        private set

    var intervalDays by mutableStateOf(0)
        private set

    fun onEvent(event: AddNotificationEvents) {
        when(event) {
            is AddNotificationEvents.SelectInterval -> {
                intervalDays = event.interval
            }

            is AddNotificationEvents.SelectHour -> {
                hour = event.hour
            }

            is AddNotificationEvents.SelectMin -> {
                min = event.min
            }

            is AddNotificationEvents.SaveNotification -> {
                if (hour in 0..23 && min in 0..59) {
                    viewModelScope.launch {
                        repository.addNotification(
                            Notification(
                                IdN = 1,
                                IdU = 1
                            )
                        )
                    }
                }

            }
        }
    }

    init {


    }
}


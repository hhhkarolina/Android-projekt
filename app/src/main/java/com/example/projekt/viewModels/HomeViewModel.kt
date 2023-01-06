package com.example.projekt.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekt.NavScreen
import com.example.projekt.data.PlantRepository
import com.example.projekt.events.HomeEvents
import com.example.projekt.events.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PlantRepository
): ViewModel() {

    val userPlants = repository.getAllUserPlantInfo()

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeEvents) {
        when(event) {
            is HomeEvents.OnPlantClick -> {
                sendUiEvent(UiEvents.Navigate(NavScreen.PlantDetails.route + "?IdP=${event.plant.IdP}"))
            }
        }
    }

    private fun sendUiEvent(event: UiEvents) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
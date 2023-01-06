package com.example.projekt.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekt.data.PlantRepository
import com.example.projekt.data.plant.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailsViewModel @Inject constructor(
    private val repository: PlantRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var plant by mutableStateOf<Plant?>(null)
        private set

    init {
        val IdP = savedStateHandle.get<Int>("IdP")!!
        viewModelScope.launch {
            repository.getPlantById(IdP).let { plant ->
                this@PlantDetailsViewModel.plant = plant
            }
        }

    }
}
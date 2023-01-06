package com.example.projekt.viewModels

import androidx.compose.runtime.collectAsState
import com.example.projekt.data.PlantRepository
import com.example.projekt.data.plant.Plant
import com.example.projekt.data.userPlant.UserPlant
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekt.events.AddPlantEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlantViewModel @Inject constructor(
    private val repository: PlantRepository
): ViewModel() {

    val plants = repository.getAllPlants()

    val types = repository.getType()

    var type by mutableStateOf("")
        private set

    var species by mutableStateOf("")
        private set

    var id by mutableStateOf(0)
        private set

    fun getPlantsByType(type: String): Flow<List<Plant>> {
        return repository.getPlantsByType(type)
    }

    fun onEvent(event: AddPlantEvents) {
        when(event) {
            is AddPlantEvents.SelectSpecies -> {
                species = event.species
                viewModelScope.launch {
                    repository.getPlantId(type, species).collect {
                        id = it
                    }
                }
            }

            is AddPlantEvents.SelectType -> {
                type = event.type
            }

            is AddPlantEvents.SaveUserPlant -> {
                if (species.isNotBlank()) {
                    viewModelScope.launch {
                        repository.addUserPlant(
                            IdP = id
                        )
                    }
                }

            }
        }
    }


}
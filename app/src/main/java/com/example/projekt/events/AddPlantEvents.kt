package com.example.projekt.events

sealed class AddPlantEvents {
    data class SelectType(val type: String): AddPlantEvents()
    data class SelectSpecies(val species: String): AddPlantEvents()
    object SaveUserPlant: AddPlantEvents()

}

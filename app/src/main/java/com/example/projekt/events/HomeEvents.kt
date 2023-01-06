package com.example.projekt.events

import com.example.projekt.data.plant.Plant

sealed class HomeEvents {
    data class OnPlantClick(val plant: Plant): HomeEvents()
}

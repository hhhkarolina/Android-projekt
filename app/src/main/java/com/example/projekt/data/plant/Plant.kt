package com.example.projekt.data.plant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true) val IdP: Int,
    val name: String,
    val scientificName: String,
    val type: String,
    val species: String,
    val sunlight: String,
    val frostResistance: String,
    val typeOfSoil: String,
    val description: String
)
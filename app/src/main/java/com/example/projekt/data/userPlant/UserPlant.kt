package com.example.projekt.data.userPlant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserPlant (
    @PrimaryKey(autoGenerate = true) val IdU: Int,
    val IdP: Int
)


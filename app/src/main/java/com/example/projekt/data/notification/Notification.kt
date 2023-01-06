package com.example.projekt.data.notification

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notification(
    @PrimaryKey(autoGenerate = true) val IdN: Int,
    val IdU: Int
)

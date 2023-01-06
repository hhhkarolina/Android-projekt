package com.example.projekt.data

import androidx.room.RoomDatabase
import com.example.projekt.data.notification.Notification
import com.example.projekt.data.notification.NotificationDao
import com.example.projekt.data.plant.Plant
import com.example.projekt.data.plant.PlantDao
import com.example.projekt.data.userPlant.UserPlant
import com.example.projekt.data.userPlant.UserPlantDao

@androidx.room.Database(entities = [Plant::class, UserPlant::class, Notification::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun plantDao(): PlantDao
    abstract fun userPlantDao(): UserPlantDao
    abstract fun notificationDao(): NotificationDao

}
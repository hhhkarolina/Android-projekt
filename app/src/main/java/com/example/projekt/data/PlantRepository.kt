package com.example.projekt.data

import com.example.projekt.data.plant.Plant
import androidx.lifecycle.LiveData
import com.example.projekt.data.notification.Notification
import com.example.projekt.data.userPlant.UserPlant
import kotlinx.coroutines.flow.Flow

interface PlantRepository {

    //plant
    fun getAllPlants(): Flow<List<Plant>>

    fun getType(): Flow<List<String>>

    suspend fun getPlantById(id: Int): Plant

    fun getPlantId(type: String, species: String): Flow<Int>

    suspend fun addPlant(plant: Plant)

    suspend fun deletePlant(plant: Plant)

    fun getPlantsByType(type: String): Flow<List<Plant>>

    fun getAllUserPlantInfo(): Flow<List<Plant>>


    //userPlant
    suspend fun addUserPlant(IdP: Int)

    suspend fun deleteUserPlant(plant: UserPlant)

    fun getAllUserPlants(): Flow<List<UserPlant>>



    //notification
    suspend fun addNotification(notification: Notification)

    suspend fun deleteNotification(notification: Notification)

    fun getAllNotifications(): Flow<List<Notification>>
}
package com.example.projekt.data

import com.example.projekt.data.plant.PlantDao
import com.example.projekt.data.userPlant.UserPlantDao
import androidx.lifecycle.LiveData
import com.example.projekt.data.notification.Notification
import com.example.projekt.data.notification.NotificationDao
import com.example.projekt.data.plant.Plant
import com.example.projekt.data.userPlant.UserPlant
import kotlinx.coroutines.flow.Flow

class PlantRepositoryImpl(
    private val plantDao: PlantDao,
    private val userPlantDao: UserPlantDao,
    private val notificationDao: NotificationDao
) : PlantRepository {

    //plant
    override fun getAllPlants(): Flow<List<Plant>> {
        return plantDao.getAllPlants()
    }

    override fun getType(): Flow<List<String>> {
        return plantDao.getType()
    }

    override suspend fun getPlantById(id: Int): Plant {
        return plantDao.getPlantById(id)
    }

    override fun getPlantId(type: String, species: String): Flow<Int> {
        return plantDao.getPlantId(type, species)
    }

    override suspend fun addPlant(plant: Plant) {
        plantDao.addPlant(plant)
    }

    override suspend fun deletePlant(plant: Plant) {
        plantDao.deletePlant(plant)
    }

    override fun getPlantsByType(type: String): Flow<List<Plant>> {
        return plantDao.getPlantsByType(type)
    }

    override fun getAllUserPlantInfo(): Flow<List<Plant>> {
        return plantDao.getAllUserPlantInfo()
    }

    //userPlant
    override suspend fun addUserPlant(IdP: Int) {
        userPlantDao.addUserPlant(IdP)
    }

    override suspend fun deleteUserPlant(plant: UserPlant) {
        userPlantDao.deleteUserPlant(plant)
    }

    override fun getAllUserPlants(): Flow<List<UserPlant>> {
        return userPlantDao.getAllUserPlants()
    }


    //notification
    override suspend fun addNotification(notification: Notification) {
        notificationDao.addNotification(notification)
    }

    override suspend fun deleteNotification(notification: Notification) {
        notificationDao.deleteNotification(notification)
    }

    override fun getAllNotifications(): Flow<List<Notification>> {
        return notificationDao.getAllNotifications()
    }


}
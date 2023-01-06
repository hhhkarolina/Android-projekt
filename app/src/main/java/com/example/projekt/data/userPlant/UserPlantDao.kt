package com.example.projekt.data.userPlant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPlantDao {
    @Query("INSERT INTO UserPlant (IdP) VALUES (:IdP)")
    suspend fun addUserPlant(IdP: Int)

    @Delete
    suspend fun deleteUserPlant(plant: UserPlant)

    @Query("SELECT * FROM UserPlant")
    fun getAllUserPlants(): Flow<List<UserPlant>>

}
package com.example.projekt.data.plant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Query("SELECT * from Plant")
    fun getAllPlants(): Flow<List<Plant>>

    @Query("SELECT DISTINCT type from Plant")
    fun getType(): Flow<List<String>>

    @Query("SELECT * from Plant WHERE IdP = :id")
    suspend fun getPlantById(id: Int): Plant

    @Query("SELECT IdP from Plant WHERE type = :type AND species = :species")
    fun getPlantId(type: String, species: String): Flow<Int>

    @Query("SELECT * from Plant WHERE type = :type")
    fun getPlantsByType(type: String): Flow<List<Plant>>

    @Query("SELECT * FROM Plant INNER JOIN UserPlant ON Plant.IdP = UserPlant.IdP")
    fun getAllUserPlantInfo(): Flow<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlant(plant: Plant)

    @Delete
    suspend fun deletePlant(plant: Plant)
}
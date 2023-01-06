package com.example.projekt.data.notification

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotification(notification: Notification)

    @Delete
    suspend fun deleteNotification(notification: Notification)

    @Query("SELECT * FROM Notification")
    fun getAllNotifications(): Flow<List<Notification>>
}
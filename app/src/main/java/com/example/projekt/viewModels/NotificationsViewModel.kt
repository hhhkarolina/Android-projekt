package com.example.projekt.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekt.data.PlantRepository
import com.example.projekt.data.notification.Notification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val repository: PlantRepository,
): ViewModel() {

    val notifications = repository.getAllNotifications()

    fun deleteNotification(notification: Notification) {
        viewModelScope.launch {
            repository.deleteNotification(notification = notification)
        }
    }

}
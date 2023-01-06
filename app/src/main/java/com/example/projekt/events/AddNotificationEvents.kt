package com.example.projekt.events

sealed class AddNotificationEvents {
    data class SelectInterval(val interval: Int): AddNotificationEvents()
    data class SelectHour(val hour: Int): AddNotificationEvents()
    data class SelectMin(val min: Int): AddNotificationEvents()
    object SaveNotification: AddNotificationEvents()

}
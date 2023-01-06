package com.example.projekt.events

sealed class UiEvents {
    data class Navigate(val route: String): UiEvents()

}
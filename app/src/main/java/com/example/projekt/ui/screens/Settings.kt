package com.example.projekt.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier

) {
    Column {
        ListItem(
            headlineText = { Text("Wyloguj") },
            leadingContent = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            },
            trailingContent = { Text("meta") }
        )
        Divider(Modifier.padding(end = 10.dp, start = 10.dp))

        ListItem(
            headlineText = { Text("Zmień język") },
            leadingContent = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            },
            trailingContent = { Text("meta") }
        )
        Divider(Modifier.padding(end = 10.dp, start = 10.dp))

        ListItem(
            headlineText = { Text("Zgłoś błąd") },
            leadingContent = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            },
            trailingContent = { Text("meta") }
        )
        Divider(Modifier.padding(end = 10.dp, start = 10.dp))
    }


}



@Preview(showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
package com.example.projekt.ui.screens


import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projekt.PlantNotificationService
import com.example.projekt.events.AddNotificationEvents
import com.example.projekt.viewModels.AddNotificationViewModel
import com.example.projekt.viewModels.AddPlantViewModel

@Composable
fun AddNotificationScreen(
    onClick: () -> Unit,
    viewModel: AddNotificationViewModel = hiltViewModel()
) {

    Column {
        DaySetter()
        TimeSetter(
            { viewModel.onEvent(AddNotificationEvents.SelectMin(it)) } ,
            { viewModel.onEvent(AddNotificationEvents.SelectHour(it)) },
            viewModel.min,
            viewModel.hour
        )
        Button(
            onClick = onClick
        ) {
            Text("test")
        }

        Button(
            onClick = { viewModel.onEvent(AddNotificationEvents.SaveNotification) },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Row {
                Text("Zapisz")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSetter(
    onMinChange: (Int) -> Unit,
    onHourChange: (Int) -> Unit,
    min: Int,
    hour: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {

        OutlinedTextField(
            value = hour.toString(),
            onValueChange = { onHourChange(inputToInt(it)) },
            label = { Text("godzina") },
            placeholder = { Text("0") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            isError = hour !in 0..23,
            modifier = Modifier
                .padding(end = 5.dp, top = 10.dp, bottom = 10.dp)
                .width(100.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = min.toString(),
            onValueChange = { onMinChange(inputToInt(it)) },
            label = { Text("minuta") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            isError = min !in 0..59,
            modifier = Modifier
                .padding(start = 5.dp, top = 10.dp, bottom = 10.dp)
                .width(100.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaySetter() {
    OutlinedTextField(
        value = "czas",
        onValueChange = {},
        label = { Text("czas") },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(10.dp)
    )

}

fun inputToInt(input: String): Int {
    return if(input == "") 0
    else input.toInt()
}





package com.example.projekt.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projekt.data.plant.Plant
import com.example.projekt.events.AddPlantEvents
import com.example.projekt.viewModels.AddPlantViewModel


@Composable
fun AddPlant(
    onClickType: () -> Unit,
    onClickSpecies: () -> Unit,
    viewModel: AddPlantViewModel = hiltViewModel()

) {
    val plants = viewModel.plants.collectAsState(initial = emptyList())
    val plantsByType = viewModel.getPlantsByType(viewModel.type).collectAsState(initial = emptyList())
    val types = viewModel.types.collectAsState(initial = emptyList())


    Column {
        ChoosePlantType(types, viewModel.type) { viewModel.onEvent(AddPlantEvents.SelectType(it)) }
        ChoosePlantSpecies(plantsByType, viewModel.type, viewModel.species) { viewModel.onEvent(AddPlantEvents.SelectSpecies(it)) }
        PhotoCard()
        SaveButton {viewModel.onEvent(AddPlantEvents.SaveUserPlant)}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoosePlantType(
    types: State<List<String>>,
    type: String,
    onTypeChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    OutlinedCard(
        onClick = { expanded = true },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
    ) {
        Text(
            text = typeText(type),
            modifier = Modifier
                .padding(10.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            for (type in types.value) {
                DropdownMenuItem(
                    text = { Text(type) },
                    onClick = { onTypeChange(type) }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoosePlantSpecies(
    plants: State<List<Plant>>,
    type: String,
    species: String,
    onSpeciesChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }


    OutlinedCard(
        onClick = { expanded = true },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        enabled = type.isNotBlank()
    ) {
        Text(
            text = speciesText(species),
            modifier = Modifier
                .padding(10.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            plants.value.forEach { plant ->
                DropdownMenuItem(
                    onClick = { onSpeciesChange(plant.species) },
                    text = { Text(plant.species) }
                )
            }
        }
    }
}

@Composable
fun SaveButton(
    savePlant: () -> Unit
) {
    Button(
        onClick = savePlant,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Text("Zapisz")
    }
}

fun typeText(
    type: String
): String {
    return type.ifBlank { "Wybierz rodzaj rośliny" }
}

fun speciesText(
    species: String
): String {
    return species.ifBlank { "Wybierz gatunek rośliny" }
}


@Composable
fun PhotoCard() {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
    ) {
        Text(
            text = "Zrób zdjęcie",
            modifier = Modifier
                .padding(10.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddPlantPreview() {
    AddPlant({}, {})
}
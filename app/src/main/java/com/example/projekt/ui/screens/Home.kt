package com.example.projekt.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projekt.R
import com.example.projekt.data.plant.Plant
import com.example.projekt.events.HomeEvents
import com.example.projekt.events.UiEvents
import com.example.projekt.viewModels.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantCard(
    userPlant: Plant,
    modifier: Modifier = Modifier,
    onEvent: (HomeEvents) -> Unit
) {


    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White)

    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_local_florist_24),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Column {
                Text(
                    //name
                    text = userPlant.name,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )

                Text(
                    //scientific
                    text = userPlant.scientificName,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigate: (UiEvents.Navigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val userPlants = viewModel.userPlants.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvents.Navigate -> onNavigate(event)
            }
        }
    }

    LazyColumn {
        items(userPlants.value) { userPlant ->
            PlantCard(
                userPlant = userPlant,
                onEvent = viewModel::onEvent,
                modifier = Modifier
                    .clickable { viewModel.onEvent(HomeEvents.OnPlantClick(userPlant)) }
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(10.dp),
            )
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    //PlantCard(onClick = {})
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    //HomeScreen(onClick = {})
}


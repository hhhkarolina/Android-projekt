package com.example.projekt.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projekt.R
import com.example.projekt.data.plant.Plant
import com.example.projekt.viewModels.PlantDetailsViewModel



@Composable
fun PlantDetails(
    onNotificationNavigation: () -> Unit,
    viewModel: PlantDetailsViewModel = hiltViewModel()
) {
    val plant = viewModel.plant

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ){
        PlantName(plant)
        DetailsNotificationCard(onNotificationNavigation)
        PlantPropertiesCard(plant)
        DescriptionCard(plant)
    }
}

@Composable
fun PlantName(
    plant: Plant?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = plant?.name ?: "abc co jest", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Text(text = plant?.scientificName ?: "hmm", fontStyle = FontStyle.Italic, color = Color.DarkGray)
    }

}

@Composable
fun DetailsNotificationCard(
    onNotificationNavigation: () -> Unit,
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_water_drop_24),
                contentDescription = "watering",
            )


            Text(
                text = "Nie ustawiono",
                modifier = Modifier
                    .width(200.dp)
                    .wrapContentWidth(Alignment.End)
            )

            IconButton(
                onClick = onNotificationNavigation,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            ) {
                Image(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "edit",
                )
            }
        }
    }
}

@Composable
fun PlantPropertiesCard(
    plant: Plant?
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Światło słoneczne", fontWeight = FontWeight.Bold)
            Text(text = plant?.sunlight ?: "")

            Spacer(Modifier.height(10.dp))

            Text(text = "Rodzaje gleby", fontWeight = FontWeight.Bold)
            Text(text = plant?.typeOfSoil ?: "")

            Spacer(Modifier.height(10.dp))

            Text(text = "Mrozoodporność", fontWeight = FontWeight.Bold)
            Text(text = plant?.frostResistance ?: "")
        }

    }
}

@Composable
fun DescriptionCard(
    plant: Plant?
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Opis", fontWeight = FontWeight.Bold)
            Text(text = plant?.description ?: "")
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PlantDetailsPreview() {
    //PlantDetails()
}


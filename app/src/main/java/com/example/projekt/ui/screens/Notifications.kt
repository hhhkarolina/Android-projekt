package com.example.projekt.ui.screens

import android.graphics.BlendModeColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projekt.R
import com.example.projekt.data.notification.Notification
import com.example.projekt.viewModels.NotificationsViewModel


@Composable
fun NotificationCard(
    notification: Notification,
    viewModel: NotificationsViewModel
) {
    var isChecked by remember { mutableStateOf(false) }
    val onClick = {
        if(isChecked) viewModel.deleteNotification(notification)
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),

        ){
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_local_florist_24),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Column {
                Text(
                    text = "9:00",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = notification.IdU.toString(),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                )


            }

            Checkbox(checked = isChecked,
                onCheckedChange = { isChecked = !isChecked},
                modifier = Modifier
                    .height(100.dp)
            )



            DeleteButton(
                onClick = onClick,
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxSize()
                    .wrapContentSize(Alignment.CenterEnd)
            )

        }
    }
}

@Composable
fun DayLabel(
    notification: Notification
) {
    Column(
        modifier = Modifier.padding(start = 10.dp, top = 5.dp)
    ){
        Text(
            text = notification.IdN.toString(),
            fontSize = 30.sp
        )
    }
}

@Composable
fun DeleteButton(
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Image(
            imageVector = Icons.Outlined.Delete,
            contentDescription = null
        )
    }
}

@Composable
fun NotificationScreen(
    viewModel: NotificationsViewModel = hiltViewModel()
) {
    val notifications = viewModel.notifications.collectAsState(initial = emptyList())

    LazyColumn {
        items(notifications.value) { notification ->
            DayLabel(notification)
            NotificationCard(notification, viewModel)
        }

    }
}


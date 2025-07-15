package com.example.artsy.ui.screens.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.artsy.R


@Composable
fun ErrorScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null)
        Text("No Connection",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.size(width = 100.dp, height = 40.dp)
        )
        {
            Text("Retry")
        }
    }
}


@Composable
fun LoadingScreen(){
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = "Loading",
        modifier = Modifier.size(200.dp).fillMaxSize()
    )}


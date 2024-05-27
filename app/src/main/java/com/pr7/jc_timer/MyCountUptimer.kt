package com.pr7.jc_timer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun MyCountUptimer() {

    var ticks by remember { mutableStateOf(0) }
    var minute by remember { mutableStateOf(0) }
    var hour by remember { mutableStateOf(0) }
    var start by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = start) {
        while (start) {
            delay(1.seconds)
            //calculating second
            ticks += 1

            //calculating minute
            if (ticks == 60) {
                ticks = 0
                minute++
            }
            if (minute == 60) {
                minute = 0
                hour++
            }
            //calculating minute


            //ended time bracked
//            if (ticks == 180) {
//                break
//            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "${if (hour < 10) "0$hour" else "$hour"}:${if (minute < 10) "0$minute" else "$minute"}:${if (ticks < 10) "0$ticks" else "$ticks"}")
        Button(onClick = { start = !start }) {
            Icon(
                imageVector = if (start) Icons.Default.Star else Icons.Default.PlayArrow,
                contentDescription = "D"
            )
            Text(text = if (start) "Pause" else "Start")
        }
        Button(onClick = {
            start = false
            ticks = 0
            minute = 0
            hour = 0
        }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "D")
            Text(text = "Stop")
        }
    }


}
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
fun MyCountDowntimer(h: Int = 0, m: Int) {
    var rest by remember { mutableStateOf(0) }
    var ticks by remember { mutableStateOf(0) }
    var minute by remember { mutableStateOf(m) }
    var hour by remember { mutableStateOf(h) }
    var start by remember { mutableStateOf(false) }
    var stop by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = stop) {
        if (m > 59) {
            hour = m / 60 //3
            rest = m % 60 //0
            minute = rest
        }
    }
    LaunchedEffect(key1 = start) {
        while (start) {
            delay(1.seconds)
            //calculating second
            if (ticks > 0) {
                ticks -= 1
            }
            if (minute == 0 && ticks==0) {
                if (hour > 0) {
                    hour--
                    minute = 60
                }
            }
            //calculating second
            //calculating minute
            if (ticks == 0) {

                if (minute > 0) {
                    ticks = 59
                    minute--
                }
            }
            //calculating minute
            //ended time bracked
            if (ticks == 0 && hour == 0 && minute == 0) {

                stop = false
                start = false
                hour = h
                minute = m
                ticks = 0
                break
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "${if (hour < 10) "0$hour" else "$hour"}:${if (minute < 10) "0$minute" else "$minute"}:${if (ticks < 10) "0$ticks" else "$ticks"}")
        Button(onClick = {
            start = !start
        }) {
            Icon(
                imageVector = if (start) Icons.Default.Star else Icons.Default.PlayArrow,
                contentDescription = "D"
            )
            Text(text = if (start) "Pause" else "Start")
        }
        Button(onClick = {
            minute = m
            hour = h
            ticks = 0
            stop = !stop
            start = false


        }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "D")
            Text(text = "Stop")
        }
    }


}
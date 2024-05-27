package com.pr7.jc_timer

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun LaunchedandDisposable(modifier: Modifier = Modifier) {

    var state by remember {
        mutableStateOf(false)
    }
    Log.d(TAG, "MainScreen: Recomposed Main :)")
    LaunchedEffect(key1 = Unit) {
        Log.d(TAG, "MainScreen: LaunchedEffect :)")
    }
    DisposableEffect(key1 = state) {
        Log.d(TAG, "MainScreen Disposable composed:( 1")
        onDispose {
            Log.d(TAG, "MainScreen Composable Disposed:( 2")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text ="${System.currentTimeMillis()} $state")
        Button(onClick = {
            state=!state
        }) {
            Text(text = "go D")
        }
    }
}

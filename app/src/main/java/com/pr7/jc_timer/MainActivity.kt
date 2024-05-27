package com.pr7.jc_timer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pr7.jc_timer.ui.theme.JC_TimerTheme
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.seconds

const val TAG = "PR77777"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC_TimerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        MainScreen()
                    }
                }
            }
        }
    }
}


@Composable
fun MainScreen(modifier: Modifier = Modifier) {

        //MyCountUptimer()
        //MyCountDowntimer(m = 120)
    
        TimerScreen(hour = 15)
    
    Text(text = System.currentTimeMillis().toString())
    Text(text = converting(System.currentTimeMillis()))



}










@Composable
fun Countdown(targetTime: Long, content: @Composable (remainingTime: Long) -> Unit) {
    var remainingTime by remember(targetTime) {
        mutableStateOf(targetTime - System.currentTimeMillis())
    }

    content.invoke(remainingTime)

    LaunchedEffect(remainingTime) {
        delay(1_000L)
        remainingTime = targetTime - System.currentTimeMillis()
    }
}







@Composable
fun TimerScreen(hour: Long) {
    val timerConverter = remember {
        mutableStateOf("")
    }
    val lastConnection = remember {
        mutableStateOf(System.currentTimeMillis())
    }
    LaunchedEffect(key1 = timerConverter.value) {
        delay(1000)
        timerConverter.value =
            converting(1000 * 60 * 60 * hour - (System.currentTimeMillis() - lastConnection.value))
    }
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 15.dp,
                    bottom = 10.dp,
                ),
            text = timerConverter.value,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@SuppressLint("DefaultLocale")
fun converting(millis: Long): String =
    String.format(
        "Hours %02d : Minutes %02d : Seconds %02d",
        TimeUnit.MILLISECONDS.toHours(millis),
        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(millis)
        ),
        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(millis)
        )


        //CONVERT
//
//        val time =(timerDate.time).minus(Calendar.getInstance().timeInMillis)
//
//        var timer by remember { mutableStateOf(time) }
//        LaunchedEffect(key1 = timer) {
//            if (timer > 0) {
//                delay(1000L)
//                timer -= 1000L
//            }
//        }
//
//        val secMilSec: Long = 1000
//        val minMilSec = 60 * secMilSec
//        val hourMilSec = 60 * minMilSec
//        val dayMilSec = 24 * hourMilSec
//        val hours = (time % dayMilSec / hourMilSec).toInt()
//        val minutes = (time % dayMilSec % hourMilSec / minMilSec).toInt()
//        val seconds = (time % dayMilSec % hourMilSec % minMilSec / secMilSec).toInt()
//
//        Text(text = String.format(" %02d:%02d:%02d", hours, minutes, seconds))

    )

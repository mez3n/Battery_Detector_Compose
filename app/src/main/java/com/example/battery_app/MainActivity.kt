package com.example.battery_app

import android.content.Context.BATTERY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.battery_app.ui.theme.Battery_AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Battery_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    Battery_Detector()
                }
            }
        }
    }
}

@Composable
fun Battery_Detector(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val batLevel = remember { mutableStateOf(100) }
    val batteryReceiver = remember { NotificationReciever(batLevel) }

    val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    context.registerReceiver(batteryReceiver, intentFilter)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        val imageResource = if (batLevel.value > 20) {
            R.drawable.battery_full
        } else {
            R.drawable.battery_low
        }
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Battery status",
            Modifier
                .size(400.dp)
                .offset(0.dp, 200.dp)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun BatteryDetectorPreview() {
    Battery_Detector()
}

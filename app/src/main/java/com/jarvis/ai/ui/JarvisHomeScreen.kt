package com.jarvis.ai.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun JarvisHomeScreen(status: String, isListening: Boolean) {
    Box(modifier = Modifier.fillMaxSize().background(JarvisDeepBlack), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = if (isListening) "LISTENING..." else "STANDBY", color = JarvisNeonBlue, fontSize = 20.sp)
            Text(text = status, color = Color.Gray, modifier = Modifier.padding(top = 10.dp))
        }
    }
}

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
fun SetupScreen(progress: Int, onFinished: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(JarvisDeepBlack), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(20.dp)) {
            Text("INITIALIZING JARVIS", color = JarvisNeonBlue, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(20.dp))
            LinearProgressIndicator(progress = progress / 100f, color = JarvisNeonBlue, modifier = Modifier.fillMaxWidth())
            Text("Downloading Brain: $progress%", color = Color.Gray, modifier = Modifier.padding(top = 10.dp))
            if (progress == 100) { Button(onClick = onFinished, modifier = Modifier.padding(top = 20.dp)) { Text("ACTIVATE") } }
        }
    }
}

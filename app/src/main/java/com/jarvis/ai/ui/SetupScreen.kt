package com.jarvis.ai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SetupScreen(progress: Int, onFinished: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(JarvisDeepBlack),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "INITIALIZING JARVIS",
                color = JarvisNeonBlue,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            LinearProgressIndicator(
                progress = progress / 100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = JarvisNeonBlue,
                backgroundColor = Color.DarkGray
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Downloading Brain Core: $progress%",
                color = Color.LightGray,
                fontSize = 14.sp
            )
            
            if (progress == 100) {
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onFinished,
                    colors = ButtonDefaults.buttonColors(backgroundColor = JarvisNeonBlue)
                ) {
                    Text("ACTIVATE JARVIS", color = Color.Black)
                }
            }
        }
    }
}

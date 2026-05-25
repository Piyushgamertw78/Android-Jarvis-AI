package com.jarvis.ai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.*
import androidx.compose.animation.rememberInfiniteTransition

@Composable
fun JarvisHomeScreen(status: String, isListening: Boolean) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(JarvisDeepBlack),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // The Jarvis Arc/Ring
            Box(
                modifier = Modifier
                    .size(200.dp * scale)
                    .clip(CircleShape)
                    .background(JarvisNeonBlue.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(JarvisDeepBlack)
                ) {
                    // Inner core animation could go here
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = if (isListening) "LISTENING..." else "STANDBY",
                color = JarvisNeonBlue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp
            )

            Text(
                text = status,
                color = Color.LightGray,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

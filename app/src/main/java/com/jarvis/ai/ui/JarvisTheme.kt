package com.jarvis.ai.ui

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val JarvisNeonBlue = Color(0xFF00E5FF)
val JarvisDeepBlack = Color(0xFF0A0A0A)
val JarvisDarkGrey = Color(0xFF1A1A1A)

val JarvisColors = darkColors(
    primary = JarvisNeonBlue,
    background = JarvisDeepBlack,
    surface = JarvisDarkGrey,
    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

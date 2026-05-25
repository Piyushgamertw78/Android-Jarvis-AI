package com.jarvis.ai.model

data class JarvisCommand(
    val action: String,      // e.g., "SEND_WHATSAPP", "PLAY_MUSIC", "TOGGLE_WIFI"
    val target: String? = null, // e.g., "Mummy", "Believer"
    val value: String? = null,  // e.g., "Hello", "On"
    val app: String? = null     // e.g., "com.whatsapp", "com.spotify.music"
)

package com.jarvis.ai.model
import android.content.Context
class LocalLLMManager(private val context: Context) {
    fun parseCommand(input: String): JarvisCommand {
        return if (input.contains("whatsapp", true)) JarvisCommand("SEND_WHATSAPP", "Mummy", "Hello") else JarvisCommand("UNKNOWN")
    }
}

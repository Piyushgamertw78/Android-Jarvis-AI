package com.jarvis.ai.model

import android.content.Context
import android.util.Log
import org.json.JSONObject

class LocalLLMManager(private val context: Context) {
    private val TAG = "LocalLLM"

    // System prompt to force the LLM to act as a JSON parser for Android commands
    private val SYSTEM_PROMPT = """
        You are JARVIS, an Android AI agent. 
        Your goal is to convert user voice commands into a strict JSON format.
        
        Allowed Actions:
        - SEND_WHATSAPP: { "action": "SEND_WHATSAPP", "target": "contact_name", "value": "message_text" }
        - PLAY_MUSIC: { "action": "PLAY_MUSIC", "target": "song_name", "app": "package_name" }
        - TOGGLE_SETTING: { "action": "TOGGLE_SETTING", "target": "setting_name", "value": "on/off" }
        - OPEN_APP: { "action": "OPEN_APP", "app": "package_name" }
        
        Examples:
        User: "Mummy ko hello bhejo" -> { "action": "SEND_WHATSAPP", "target": "Mummy", "value": "hello" }
        User: "Spotify pe believer chalao" -> { "action": "PLAY_MUSIC", "target": "believer", "app": "com.spotify.music" }
        User: "Wifi band karo" -> { "action": "TOGGLE_SETTING", "target": "wifi", "value": "off" }
        
        Respond ONLY with the JSON object. No conversational text.
    """.trimIndent()

    fun parseCommand(userInput: String): JarvisCommand {
        Log.d(TAG, "Parsing input: $userInput")
        
        // In a real implementation, this calls the MediaPipe / Llama.cpp inference
        // val response = llmInference.generate(SYSTEM_PROMPT + "\nUser: " + userInput)
        
        // Mocking the LLM response for the structural example
        // The real app will load the .bin model file from /assets
        val mockResponse = simulateLLM(userInput) 
        
        return try {
            val json = JSONObject(mockResponse)
            JarvisCommand(
                action = json.getString("action"),
                target = json.optString("target"),
                value = json.optString("value"),
                app = json.optString("app")
            )
        } catch (e: Exception) {
            JarvisCommand(action = "UNKNOWN")
        }
    }

    private fun simulateLLM(input: String): String {
        // Simple regex-based simulation for the demonstration of the flow
        return when {
            input.contains("whatsapp", ignoreCase = true) || input.contains("bhejo", ignoreCase = true) -> 
                """{ "action": "SEND_WHATSAPP", "target": "Mummy", "value": "hello" }"""
            input.contains("play", ignoreCase = true) || input.contains("chalao", ignoreCase = true) -> 
                """{ "action": "PLAY_MUSIC", "target": "Believer", "app": "com.spotify.music" }"""
            input.contains("wifi", ignoreCase = true) -> 
                """{ "action": "TOGGLE_SETTING", "target": "wifi", "value": "off" }"""
            else -> """{ "action": "OPEN_APP", "app": "com.google.android.youtube" }"""
        }
    }
}

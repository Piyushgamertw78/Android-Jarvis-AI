package com.jarvis.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jarvis.ai.automation.*
import com.jarvis.ai.model.*
import com.jarvis.ai.ui.*
import com.jarvis.ai.voice.*
import androidx.compose.runtime.*
import android.util.Log

class MainActivity : ComponentActivity() {
    private lateinit var voiceManager: VoiceManager
    private lateinit var llmManager: LocalLLMManager
    private lateinit var workflowEngine: WorkflowEngine
    private var jarvisService: JarvisAccessibilityService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        voiceManager = VoiceManager(this)
        llmManager = LocalLLMManager(this)
        
        // We need the service instance. In a real app, we'd use a singleton or 
        // Bind to the Accessibility Service.
        // workflowEngine = WorkflowEngine(this, jarvisService!!)

        var status by remember { mutableStateOf("Hello, I am Jarvis") }
        var isListening by remember { mutableStateOf(false) }

        setContent {
            JarvisHomeScreen(status = status, isListening = isListening)
        }

        // Start the JARVIS loop
        startJarvisLoop { input ->
            status = "Processing: $input"
            val command = llmManager.parseCommand(input)
            
            if (command.action != "UNKNOWN") {
                voiceManager.speak("Executing ${command.action}")
                // workflowEngine.execute(command)
                status = "Successfully executed ${command.action}"
            } else {
                voiceManager.speak("I'm sorry, I couldn't understand that.")
                status = "Command unknown"
            }
        }
    }

    private fun startJarvisLoop(onCommandDetected: (String) -> Unit) {
        voiceManager.startListening { text ->
            onCommandDetected(text)
        }
    }
}

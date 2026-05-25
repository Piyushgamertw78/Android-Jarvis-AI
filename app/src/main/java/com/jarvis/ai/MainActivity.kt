package com.jarvis.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jarvis.ai.automation.*
import com.jarvis.ai.model.*
import com.jarvis.ai.ui.*
import com.jarvis.ai.voice.*
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    private lateinit var voiceManager: VoiceManager
    private lateinit var llmManager: LocalLLMManager
    private lateinit var downloader: ModelDownloader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        voiceManager = VoiceManager(this)
        llmManager = LocalLLMManager(this)
        downloader = ModelDownloader(this)

        var appState by remember { mutableStateOf("SETUP") }
        var downloadProgress by remember { mutableStateOf(0) }
        var jarvisStatus by remember { mutableStateOf("Hello, I am Jarvis") }
        var isListening by remember { mutableStateOf(false) }

        setContent {
            if (appState == "SETUP") {
                SetupScreen(progress = downloadProgress) {
                    appState = "READY"
                }
            } else {
                JarvisHomeScreen(status = jarvisStatus, isListening = isListening)
            }
        }

        if (!downloader.isModelDownloaded()) {
            downloader.downloadModel(
                onProgress = { progress -> downloadProgress = progress },
                onComplete = { 
                    llmManager.initModel(downloader.getModelPath())
                    // Note: In a real app, we'd update appState via a ViewModel/State object
                }
            )
        } else {
            appState = "READY"
            llmManager.initModel(downloader.getModelPath())
        }

        // Start the JARVIS loop once ready
        startJarvisLoop { input ->
            jarvisStatus = "Processing: $input"
            val command = llmManager.parseCommand(input)
            voiceManager.speak("Executing ${command.action}")
            jarvisStatus = "Successfully executed ${command.action}"
        }
    }

    private fun startJarvisLoop(onCommandDetected: (String) -> Unit) {
        voiceManager.startListening { text ->
            onCommandDetected(text)
        }
    }
}

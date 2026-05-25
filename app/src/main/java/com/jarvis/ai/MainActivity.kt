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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val voiceManager = VoiceManager(this)
        val llmManager = LocalLLMManager(this)
        val downloader = ModelDownloader(this)
        var appState by remember { mutableStateOf("SETUP") }
        var progress by remember { mutableStateOf(0) }
        var status by remember { mutableStateOf("Welcome, Sir") }
        setContent {
            if (appState == "SETUP") { SetupScreen(progress) { appState = "READY" } } else { JarvisHomeScreen(status, false) }
        }
        if (!downloader.isModelDownloaded()) {
            downloader.downloadModel({ p -> progress = p }, { appState = "READY" })
        } else { appState = "READY" }
        voiceManager.startListening { input ->
            status = "Processing $input"
            val cmd = llmManager.parseCommand(input)
            voiceManager.speak("Executing ${cmd.action}")
        }
    }
}

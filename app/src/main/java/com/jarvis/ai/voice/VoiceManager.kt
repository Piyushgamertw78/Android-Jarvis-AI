package com.jarvis.ai.voice
import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*
class VoiceManager(context: Context) : TextToSpeech.OnInitListener {
    private var tts = TextToSpeech(context, this)
    override fun onInit(status: Int) { if (status == TextToSpeech.SUCCESS) tts.language = Locale.US }
    fun speak(text: String) { tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null) }
    fun startListening(onResult: (String) -> Unit) { onResult("whatsapp mummy hello") }
}

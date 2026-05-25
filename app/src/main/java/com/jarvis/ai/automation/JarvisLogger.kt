package com.jarvis.ai.automation

import android.util.Log
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object JarvisLogger {
    private const val TAG = "JARVIS_LOG"
    private const val LOG_FILE = "jarvis_debug.log"

    fun log(level: String, message: String) {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val logEntry = "[$timestamp] $level: $message"
        
        Log.d(TAG, logEntry)
        
        try {
            FileWriter(LOG_FILE, true).use { writer ->
                writer.append(logEntry + "\n")
            }
        } catch (e: IOException) {
            Log.e(TAG, "Failed to write to log file", e)
        }
    }

    fun info(msg: String) = log("INFO", msg)
    fun error(msg: String) = log("ERROR", msg)
    fun debug(msg: String) = log("DEBUG", msg)
}

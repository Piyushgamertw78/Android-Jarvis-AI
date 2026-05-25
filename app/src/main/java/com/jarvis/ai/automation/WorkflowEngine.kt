package com.jarvis.ai.automation
import android.content.Context
import com.jarvis.ai.model.JarvisCommand
class WorkflowEngine(private val context: Context, private val service: JarvisAccessibilityService) {
    fun execute(command: JarvisCommand) {
        if (command.action == "SEND_WHATSAPP") {
            val intent = context.packageManager.getLaunchIntentForPackage("com.whatsapp")
            context.startActivity(intent)
        }
    }
}

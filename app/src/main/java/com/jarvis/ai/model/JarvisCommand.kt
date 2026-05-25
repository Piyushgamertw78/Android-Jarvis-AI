package com.jarvis.ai.model
data class JarvisCommand(val action: String, val target: String? = null, val value: String? = null, val app: String? = null)

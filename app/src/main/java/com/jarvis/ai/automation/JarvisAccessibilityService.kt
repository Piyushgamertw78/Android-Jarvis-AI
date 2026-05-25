package com.jarvis.ai.automation
import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.util.Log
class JarvisAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}
    fun clickText(text: String): Boolean {
        val rootNode = rootInActiveWindow ?: return false
        val nodes = rootNode.findAccessibilityNodeInfosByText(text)
        if (nodes != null && nodes.isNotEmpty()) { nodes[0].performAction(AccessibilityNodeInfo.ACTION_CLICK); return true }
        return false
    }
    fun typeText(text: String): Boolean {
        val rootNode = rootInActiveWindow ?: return false
        val nodes = rootNode.findFocus()
        if (nodes != null) {
            val bundle = android.os.Bundle()
            bundle.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text)
            nodes.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, bundle)
            return true
        }
        return false
    }
}

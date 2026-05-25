package com.jarvis.ai.automation

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.util.Log
import android.os.Bundle

class JarvisAccessibilityService : AccessibilityService() {
    private val TAG = "JarvisAutomation"

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // We monitor events to verify execution (e.g., checking if a screen changed)
    }

    override fun onInterrupt() {
        Log.e(TAG, "Service Interrupted")
    }

    /**
     * Core function to find a node by text and click it
     */
    fun clickText(text: String): Boolean {
        val rootNode = rootInActiveWindow ?: return false
        val nodes = rootNode.findAccessibilityNodeInfosByText(text)
        if (nodes != null && nodes.isNotEmpty()) {
            val node = nodes[0]
            node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            Log.d(TAG, "Clicked on: $text")
            node.recycle()
            return true
        }
        Log.w(TAG, "Could not find node with text: $text")
        return false
    }

    /**
     * Core function to find a node and set text (Typing)
     */
    fun typeText(text: String, targetText: String? = null): Boolean {
        val rootNode = rootInActiveWindow ?: return false
        
        // If targetText is provided, find the field near it or with that hint
        val nodes = if (targetText != null) {
            rootNode.findAccessibilityNodeInfosByText(targetText)
        } else {
            // Fallback: find the currently focused node
            listOf(rootNode) // Simplified for now
        }

        for (node in nodes) {
            if (node.isEditable) {
                val bundle = Bundle()
                bundle.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text)
                node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, bundle)
                Log.d(TAG, "Typed '$text' into field")
                node.recycle()
                return true
            }
        }
        return false
    }

    /**
     * Find an element by its Resource ID (for deterministic app control)
     */
    fun clickResourceId(resourceId: String): Boolean {
        val rootNode = rootInActiveWindow ?: return false
        val node = rootNode.findAccessibilityNodeInfosByViewId(resourceId)
        if (node != null && node.isNotEmpty()) {
            node[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
            node[0].recycle()
            return true
        }
        return false
    }
}

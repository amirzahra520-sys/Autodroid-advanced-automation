package com.autodroid.automation.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.autodroid.automation.engine.ExecutionEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AutomationService : AccessibilityService() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private lateinit var executionEngine: ExecutionEngine

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
        executionEngine = ExecutionEngine(this)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Handle events if needed for macro recording
    }

    override fun onInterrupt() {
        instance = null
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }

    fun click(x: Float, y: Float) {
        val path = Path()
        path.moveTo(x, y)
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 100))
            .build()
        dispatchGesture(gesture, null, null)
    }

    fun clickNode(node: AccessibilityNodeInfo) {
        node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
    }

    fun findNodesByText(text: String): List<AccessibilityNodeInfo> {
        return rootInActiveWindow?.findAccessibilityNodeInfosByText(text) ?: emptyList()
    }

    fun findNodesById(id: String): List<AccessibilityNodeInfo> {
        return rootInActiveWindow?.findAccessibilityNodeInfosByViewId(id) ?: emptyList()
    }

    companion object {
        var instance: AutomationService? = null
    }
}

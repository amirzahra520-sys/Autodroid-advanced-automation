package com.autodroid.automation.recorder

import android.view.accessibility.AccessibilityEvent
import com.autodroid.automation.data.Instruction
import com.autodroid.automation.data.InstructionType

class MacroRecorder {
    private val recordedSteps = mutableListOf<Instruction>()
    private var isRecording = false
    private var lastEventTime = 0L

    fun startRecording() {
        isRecording = true
        recordedSteps.clear()
        lastEventTime = System.currentTimeMillis()
    }

    fun stopRecording(): List<Instruction> {
        isRecording = false
        return recordedSteps.toList()
    }

    fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (!isRecording) return

        val currentTime = System.currentTimeMillis()
        val delay = currentTime - lastEventTime
        lastEventTime = currentTime

        // Add delay step
        recordedSteps.add(Instruction(type = InstructionType.DELAY, params = delay.toString(), order = recordedSteps.size, scriptId = 0))

        when (event.eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED -> {
                val text = event.text.firstOrNull()?.toString() ?: ""
                if (text.isNotEmpty()) {
                    recordedSteps.add(Instruction(
                        type = InstructionType.CLICK_TEXT,
                        params = text,
                        order = recordedSteps.size,
                        scriptId = 0
                    ))
                }
            }
            // Add more event types (scroll, long click, etc.)
        }
    }
}

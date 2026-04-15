package com.autodroid.automation.engine

import android.util.Log
import com.autodroid.automation.data.Instruction
import com.autodroid.automation.data.InstructionType
import com.autodroid.automation.service.AutomationService
import kotlinx.coroutines.delay

class ExecutionEngine(private val service: AutomationService) {

    private val variables = mutableMapOf<String, Any>()

    suspend fun executeScript(instructions: List<Instruction>) {
        var pc = 0
        while (pc < instructions.size) {
            val instruction = instructions[pc]
            when (instruction.type) {
                InstructionType.CLICK_TEXT -> {
                    val text = instruction.params
                    val nodes = service.findNodesByText(text)
                    if (nodes.isNotEmpty()) {
                        service.clickNode(nodes[0])
                    }
                }
                InstructionType.DELAY -> {
                    val ms = instruction.params.toLongOrNull() ?: 1000L
                    delay(ms)
                }
                InstructionType.SET_VARIABLE -> {
                    // Parse "name=value"
                    val parts = instruction.params.split("=")
                    if (parts.size == 2) {
                        variables[parts[0]] = parts[1]
                    }
                }
                InstructionType.IF_CONDITION -> {
                    // Simple check: varName==value
                    val parts = instruction.params.split("==")
                    if (parts.size == 2) {
                        val varName = parts[0]
                        val expected = parts[1]
                        if (variables[varName]?.toString() != expected) {
                            // Skip to next END_IF or similar (simplified for now)
                        }
                    }
                }
                // Add more handlers...
                else -> Log.d("ExecutionEngine", "Unsupported instruction: ${instruction.type}")
            }
            pc++
        }
    }
}

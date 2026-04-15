package com.autodroid.automation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.autodroid.automation.data.Instruction
import com.autodroid.automation.data.InstructionType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScriptEditorScreen(scriptName: String, onSave: () -> Unit) {
    var instructions by remember { mutableStateOf(mutableListOf<Instruction>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editing: $scriptName") },
                actions = {
                    IconButton(onClick = onSave) {
                        Icon(Icons.Default.Save, contentDescription = "Save")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Add new instruction dialog
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Step")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(instructions) { instruction ->
                InstructionItem(instruction)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun InstructionItem(instruction: Instruction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(instruction.type.name, style = MaterialTheme.typography.labelSmall)
                Text(instruction.params, style = MaterialTheme.typography.bodyLarge)
            }
            IconButton(onClick = { /* Delete */ }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

package com.autodroid.automation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onOpenSettings: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AutoDroid Console", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = onOpenSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Create Script */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add Script")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            StatusCard()
            Spacer(modifier = Modifier.height(24.dp))
            Text("Active Scripts", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            ScriptList()
        }
    }
}

@Composable
fun StatusCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Engine Status", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
            Text("READY", fontSize = 24.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .padding(end = 4.dp)
                ) // Green dot placeholder
                Text("Accessibility Service: ENABLED", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ScriptList() {
    val mockScripts = listOf("Auto-Login", "Daily Rewards", "Screen Monitor")
    LazyColumn {
        items(mockScripts) { script ->
            ListItem(
                headlineContent = { Text(script) },
                supportingContent = { Text("Last run: 2h ago") },
                trailingContent = {
                    IconButton(onClick = { /* Run */ }) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Run")
                    }
                }
            )
            Divider()
        }
    }
}

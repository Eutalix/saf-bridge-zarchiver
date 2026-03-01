package com.eutalix.safbridge.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eutalix.safbridge.data.PreferencesManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    prefsManager: PreferencesManager,
    onBack: () -> Unit
) {
    var readOnly by remember { mutableStateOf(prefsManager.forceReadOnly) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Security", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(8.dp))
            
            ListItem(
                headlineContent = { Text("Force Read-Only Mode") },
                supportingContent = { Text("Prevents any write operations from ZArchiver. Recommended if you are experiencing file corruption with the Free version.") },
                trailingContent = {
                    Switch(
                        checked = readOnly,
                        onCheckedChange = {
                            readOnly = it
                            prefsManager.forceReadOnly = it
                        }
                    )
                }
            )
            Divider()
            Spacer(Modifier.height(16.dp))
            
            Text("About", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(8.dp))
            Text("SAF Plugin for ZArchiver v1.0", style = MaterialTheme.typography.bodyMedium)
            Text("Developed by Eutalix", style = MaterialTheme.typography.bodySmall)
        }
    }
}
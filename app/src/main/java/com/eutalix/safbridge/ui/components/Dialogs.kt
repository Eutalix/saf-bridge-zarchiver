package com.eutalix.safbridge.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*

/**
 * A simple dialog to rename a folder mapping alias.
 */
@Composable
fun RenameDialog(
    currentName: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var text by remember { mutableStateOf(currentName) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Rename Folder Alias") },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("New Name") },
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(onClick = { 
                if (text.isNotBlank()) onConfirm(text) 
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
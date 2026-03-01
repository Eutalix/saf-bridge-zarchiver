package com.eutalix.safbridge.data

import android.content.Context
import android.net.Uri
import androidx.core.content.edit

/**
 * Handles data persistence for folder mappings.
 * 
 * We use SharedPreferences because it is lightweight and allows the ContentProvider
 * to read data synchronously without the overhead of a Room database.
 */
class PreferencesManager(context: Context) {
    
    // The filename "accounts" is strategic. We keep mapping data separate from general settings.
    private val prefs = context.getSharedPreferences("accounts", Context.MODE_PRIVATE)
    
    private val settings = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    /**
     * Returns the list of configured folders.
     * @return List of Pairs (UriString, DisplayName) sorted by name.
     */
    fun getAccounts(): List<Pair<String, String>> {
        return prefs.all.map { Pair(it.key, it.value.toString()) }
            .sortedBy { it.second }
    }

    /**
     * Saves a folder mapping.
     * Key: The URI String (acts as the unique ID).
     * Value: The user-friendly name.
     */
    fun saveAccount(uri: Uri, name: String) {
        prefs.edit { putString(uri.toString(), name) }
    }

    fun removeAccount(uriString: String) {
        prefs.edit { remove(uriString) }
    }

    fun renameAccount(uriString: String, newName: String) {
        // We only update the value (name), keeping the key (URI) intact.
        prefs.edit { putString(uriString, newName) }
    }
    
    /**
     * Security Configuration:
     * Forces "Read-Only" mode globally. Useful if the user wants to prevent 
     * accidental writes or if the ZArchiver Free version is causing corruption.
     */
    var forceReadOnly: Boolean
        get() = settings.getBoolean("force_readonly", false)
        set(value) = settings.edit { putBoolean("force_readonly", value) }
}
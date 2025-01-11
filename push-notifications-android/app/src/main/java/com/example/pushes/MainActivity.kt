package com.example.pushes

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.pushes.client.Client
import com.example.pushes.client.MockClient
import com.example.pushes.databinding.ActivityMainBinding
import com.example.pushes.localstorage.InMemoryLocalStorage
import com.example.pushes.localstorage.LocalStorage
import com.example.pushes.localstorage.LocalStorage.Key
import java.util.UUID

class MainActivity : Activity() {

    private val client: Client = MockClient()
    private val localStorage: LocalStorage = InMemoryLocalStorage()

    private lateinit var logInButton: Button
    private lateinit var logOutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logInButton = findViewById(R.id.logInButton)
        logOutButton = findViewById(R.id.logOutButton)

        logInButton.setOnClickListener {
            val userId = localStorage.getValue(Key.USER_ID)
            if (userId == null) {
                val newUserId = client.getAnyUserId()
                val newDeviceId = UUID.randomUUID()
                localStorage.putValue(Key.USER_ID, newUserId.toString())
                localStorage.putValue(Key.DEVICE_ID, newDeviceId.toString())
                Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                refresh()
            }
        }

        logOutButton.setOnClickListener {
            val deviceId = localStorage.getValue(Key.DEVICE_ID)
            localStorage.clean()
            if (deviceId != null) {
                client.removeDeviceToken(deviceId)
            }
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            refresh()
        }

        refresh()
    }

    private fun refresh() {
        logInButton.isEnabled = !isLoggedIn()
        logOutButton.isEnabled = isLoggedIn()

        findViewById<TextView>(R.id.userIdField).text =
            "User ID: ${localStorage.getValue(Key.USER_ID) ?: "[Logged out]"}"
        findViewById<TextView>(R.id.deviceIdField).text =
            "Device ID: ${localStorage.getValue(Key.DEVICE_ID) ?: "[Logged out]"} "
    }

    private fun isLoggedIn(): Boolean {
        return localStorage.getValue(Key.USER_ID) != null
    }
}

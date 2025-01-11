package com.example.pushes

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pushes.client.MockClient
import com.example.pushes.localstorage.InMemoryLocalStorage
import com.example.pushes.service.AuthenticationService

class MainActivity : Activity() {

    private val authenticationService: AuthenticationService = AuthenticationService(
        InMemoryLocalStorage(),
        MockClient(),
    )

    private lateinit var logInButton: Button
    private lateinit var logOutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logInButton = findViewById(R.id.logInButton)
        logOutButton = findViewById(R.id.logOutButton)

        logInButton.setOnClickListener {
            authenticationService.logIn()
            displayMessage("Logged in")
            refresh()
        }

        logOutButton.setOnClickListener {
            authenticationService.logOut()
            displayMessage("Logged out")
            refresh()
        }

        refresh()
    }

    private fun refresh() {
        findViewById<TextView>(R.id.userIdField).text =
            "User ID: ${authenticationService.getUserId() ?: "[Logged out]"}"
        findViewById<TextView>(R.id.deviceIdField).text =
            "Device ID: ${authenticationService.getDeviceId() ?: "[Logged out]"} "
        logInButton.isEnabled = !authenticationService.isLoggedIn()
        logOutButton.isEnabled = authenticationService.isLoggedIn()
    }

    private fun displayMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}

package com.example.pushes.service

import com.example.pushes.client.Client
import com.example.pushes.localstorage.LocalStorage
import com.example.pushes.localstorage.LocalStorage.Key
import java.util.UUID.randomUUID

class AuthenticationService(
    private val localStorage: LocalStorage,
    private val client: Client,
) {

    fun logIn() {
        if (isLoggedIn()) {
            throw IllegalStateException("Already logged in")
        }
        val userId = client.getAnyUserId()
        val deviceId = "U${userId}D${randomUUID().toString().substring(0, 6)}"
        localStorage.putValue(Key.USER_ID, userId.toString())
        localStorage.putValue(Key.DEVICE_ID, deviceId)
    }

    fun logOut() {
        if (!isLoggedIn()) {
            throw IllegalStateException("Already logged out")
        }
        val deviceId = localStorage.getValue(Key.DEVICE_ID)
        localStorage.clean()
        if (deviceId != null) {
            client.removeDeviceToken(deviceId)
        }
    }

    fun isLoggedIn(): Boolean {
        return localStorage.getValue(Key.USER_ID) != null
    }

    fun getUserId(): String? = localStorage.getValue(Key.USER_ID)

    fun getDeviceId(): String? = localStorage.getValue(Key.DEVICE_ID)
}

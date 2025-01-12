package com.example.pushes.service

import com.example.pushes.client.Client
import com.example.pushes.localstorage.LocalStorage
import com.example.pushes.localstorage.LocalStorage.Key
import com.google.firebase.messaging.FirebaseMessaging

class NotificationService(
    private val client: Client,
    private val localStorage: LocalStorage,
) {

    fun fetchDeviceToken(onSuccess: (String) -> Unit) {
        FirebaseMessaging.getInstance().token.addOnSuccessListener(onSuccess)
    }

    fun registerDeviceToken() {
        fetchDeviceToken { token ->
            val userId = localStorage.getValue(Key.USER_ID)
            val deviceId = localStorage.getValue(Key.DEVICE_ID)
            if (userId != null && deviceId != null) {
                client.registerDeviceToken(userId.toInt(), deviceId, token)
            }
        }
    }
}

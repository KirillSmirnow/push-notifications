package com.example.pushes.messaging

import android.util.Log
import com.example.pushes.ObjectFactory
import com.example.pushes.service.NotificationService
import com.google.firebase.messaging.FirebaseMessagingService

class MainMessagingService : FirebaseMessagingService() {

    private val notificationService: NotificationService = ObjectFactory.notificationService

    override fun onNewToken(token: String) {
        Log.i(javaClass.simpleName, "New token: $token")
        notificationService.registerDeviceToken()
    }
}

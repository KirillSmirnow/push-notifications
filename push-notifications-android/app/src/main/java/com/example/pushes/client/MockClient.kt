package com.example.pushes.client

import android.util.Log

class MockClient : Client {

    override fun getAnyUserId(): Int {
        return 1
    }

    override fun registerDeviceToken(userId: Int, deviceId: String, token: String) {
        Log.i(javaClass.simpleName, "Registered device token for $deviceId")
    }

    override fun removeDeviceToken(deviceId: String) {
        Log.i(javaClass.simpleName, "Removed device token for $deviceId")
    }
}

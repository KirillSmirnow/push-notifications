package com.example.pushes.client

interface Client {

    fun getAnyUserId(responseCallback: (Int) -> Unit)

    fun registerDeviceToken(userId: Int, deviceId: String, token: String)

    fun removeDeviceToken(deviceId: String)
}

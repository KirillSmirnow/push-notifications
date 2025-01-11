package com.example.pushes.client

interface Client {

    fun getAnyUserId(): Int

    fun registerDeviceToken(userId: Int, deviceId: String, token: String)

    fun removeDeviceToken(deviceId: String)
}

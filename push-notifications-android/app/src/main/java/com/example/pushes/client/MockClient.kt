package com.example.pushes.client

class MockClient : Client {

    override fun getAnyUserId(): Int {
        return 1
    }

    override fun registerDeviceToken(userId: Int, deviceId: String, token: String) {
    }

    override fun removeDeviceToken(deviceId: String) {
    }
}

package com.example.pushes.client

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class RealClient(
    private val baseUrl: String = "http://192.168.0.101:8080",
) : Client {

    private val client = OkHttpClient()

    override fun getAnyUserId(responseCallback: (Int) -> Unit) {
        val request = Request.Builder()
            .get()
            .url("$baseUrl/users/any")
            .build()
        call(request) { response -> responseCallback(response!!.toInt()) }
    }

    override fun registerDeviceToken(userId: Int, deviceId: String, token: String) {
        val request = Request.Builder()
            .post(token.toRequestBody(contentType = "text/plain".toMediaType()))
            .url("$baseUrl/users/$userId/devices/$deviceId/tokens")
            .build()
        call(request)
    }

    override fun removeDeviceToken(deviceId: String) {
        val request = Request.Builder()
            .delete()
            .url("$baseUrl/devices/$deviceId/tokens")
            .build()
        call(request)
    }

    private fun call(request: Request, responseCallback: ((String?) -> Unit)? = null) {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.w(javaClass.simpleName, e.javaClass.simpleName + ": " + e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                val stringResponse = response.body?.string()
                Log.i(javaClass.simpleName, response.code.toString() + ": " + stringResponse)
                responseCallback?.invoke(stringResponse)
            }
        })
    }
}

package com.example.pushes

import com.example.pushes.client.Client
import com.example.pushes.client.MockClient
import com.example.pushes.localstorage.InMemoryLocalStorage
import com.example.pushes.localstorage.LocalStorage
import com.example.pushes.service.AuthenticationService
import com.example.pushes.service.NotificationService

object ObjectFactory {

    val localStorage: LocalStorage = InMemoryLocalStorage()

    val client: Client = MockClient()

    val notificationService: NotificationService = NotificationService(
        client,
        localStorage,
    )

    val authenticationService: AuthenticationService = AuthenticationService(
        localStorage,
        client,
        notificationService,
    )
}

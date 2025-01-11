package com.example.pushes.localstorage

interface LocalStorage {

    fun getValue(key: Key): String?

    fun putValue(key: Key, value: String?)

    fun clean()

    enum class Key {
        USER_ID,
        DEVICE_ID,
    }
}

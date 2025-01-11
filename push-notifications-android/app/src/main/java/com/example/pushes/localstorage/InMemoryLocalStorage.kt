package com.example.pushes.localstorage

import java.util.concurrent.ConcurrentHashMap

class InMemoryLocalStorage : LocalStorage {

    private val values: MutableMap<LocalStorage.Key, String?> =
        ConcurrentHashMap<LocalStorage.Key, String?>()

    override fun getValue(key: LocalStorage.Key): String? = values[key]

    override fun putValue(key: LocalStorage.Key, value: String?) {
        values[key] = value
    }

    override fun clean() = values.clear()
}

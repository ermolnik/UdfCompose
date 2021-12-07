package ru.ermolnik.arch.ui.data

import kotlinx.coroutines.delay
import java.lang.Exception

internal object CounterRepository {
    private var counter = 0

    suspend fun increment(): Int {
        delay(DELAY_TIME)
        return ++counter
    }

    suspend fun decrement(): Int {
        delay(DELAY_TIME)
        return --counter
    }

    suspend fun generateError(): Throwable{
        delay(DELAY_TIME)
        return Exception("Джун все сломал")
    }

    private const val DELAY_TIME = 1000L
}
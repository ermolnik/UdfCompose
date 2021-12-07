package ru.ermolnik.arch.ui.counter_vm

import androidx.compose.runtime.Immutable

@Immutable
internal data class CounterState(
    val counter: Int = 0,
    val isLoading: Boolean = false
) {
    companion object {
        val Empty = CounterState()
    }
}
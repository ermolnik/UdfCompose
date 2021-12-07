package ru.ermolnik.arch.ui.counter_vm

sealed class CounterEffect {
    object OpenDetailsAction: CounterEffect()
}

sealed class CounterActions {
    object OnIncrementClick: CounterActions()
    object OnDecrementClick: CounterActions()
}
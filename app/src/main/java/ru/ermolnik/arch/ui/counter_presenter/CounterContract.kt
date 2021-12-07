package ru.ermolnik.arch.ui.counter_presenter

import java.io.Serializable

internal interface CounterContract {
    interface View {
        fun updateCounter(): Int?
    }

    interface Presenter: Serializable {
        val counter: Int
        fun restoreDataState(): Int?
        fun onCounterUpdate(): Int
    }
}
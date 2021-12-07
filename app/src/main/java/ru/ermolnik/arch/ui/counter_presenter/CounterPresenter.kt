package ru.ermolnik.arch.ui.counter_presenter

internal class CounterPresenter : CounterContract.Presenter {

    override var counter = 0

    override fun restoreDataState(): Int {
        return counter
    }

    override fun onCounterUpdate(): Int {
        return counter++
    }
}
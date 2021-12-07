package ru.ermolnik.arch.ui.counter_presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

internal class CounterActivity : ComponentActivity(), CounterContract.View {
    private var presenter: CounterContract.Presenter? = null

    override fun updateCounter(): Int? {
        return presenter?.onCounterUpdate().apply {
            setUi(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        

        if (savedInstanceState?.containsKey(SAVED_STATE) == true) {
            val savedState = savedInstanceState.get(SAVED_STATE)
            if (savedState is CounterContract.Presenter) {
                restoreState(savedState)
            } else {
                initState()
            }
        } else {
            initState()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVED_STATE, presenter)
    }

    private fun restoreState(savedState: CounterContract.Presenter) {
        presenter = savedState
        setUi(presenter?.counter ?: 0)
    }

    private fun initState() {
        setPresenter(CounterPresenter())
        setUi()
    }

    private fun setUi(counter: Int? = null) {
        runOnUiThread {
            setContent {
                CounterScreen(counter ?: 0) {
                    updateCounter()
                }
            }
        }
    }

    private fun setPresenter(presenter: CounterContract.Presenter) {
        this.presenter = presenter
    }

    companion object {
        const val SAVED_STATE = "saved"
    }
}
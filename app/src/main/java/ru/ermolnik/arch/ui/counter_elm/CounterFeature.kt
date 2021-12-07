package ru.ermolnik.arch.ui.counter_elm

import android.util.Log
import androidx.compose.runtime.Immutable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ermolnik.arch.ui.data.CounterRepository

internal object CounterFeature {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e(this::class.simpleName,"Caught $exception")
    }
    val actor = CounterActor(CounterRepository)

    private val localState = MutableStateFlow(State())
    val state = localState.asStateFlow()

    class CounterActor(private val counterRepository: CounterRepository) {
        fun execute(cmd: Cmd) = when (cmd) {
            is Cmd.IncrementValue -> {
                CoroutineScope(Dispatchers.IO).launch(handler) {
                    localState.emit(State(isLoading = true))
                    localState.emit(
                        Reducer.reduce(
                            Event.System.ValueLoaded(counterRepository.increment()),
                            state.value
                        )
                    )
                }
            }
            is Cmd.DecrementValue -> {
                CoroutineScope(Dispatchers.IO).launch(handler) {
                    localState.emit(State(isLoading = true))
                    localState.emit(
                        Reducer.reduce(
                            Event.System.ValueLoaded(counterRepository.decrement()),
                            state.value
                        )
                    )
                }
            }
        }
    }

    @Immutable
    data class State(
        val counter: Int = 0,
        val error: Throwable? = null,
        val isLoading: Boolean = false
    )

    sealed class Event {
        sealed class User : Event() {
            object Init : User()
            object OnIncrementClick : User()
            object OnDecrementClick : User()
        }

        sealed class System : Event() {
            data class ValueLoaded(val value: Int) : System()
            data class ErrorLoadingValue(val error: Throwable) : System()
        }
    }

    sealed class Cmd {
        object IncrementValue : Cmd()
        object DecrementValue : Cmd()
    }

    object Reducer {
        fun reduce(event: Event, state: State): State {
            return when (event) {
                is Event.User.Init -> {
                    state
                }
                is Event.User.OnIncrementClick -> {
                    actor.execute(Cmd.IncrementValue)
                    state
                }
                is Event.User.OnDecrementClick -> {
                    actor.execute(Cmd.DecrementValue)
                    state
                }
                is Event.System.ValueLoaded -> {
                    state.copy(counter = event.value, isLoading = false)
                }
                is Event.System.ErrorLoadingValue -> {
                    state.copy(error = event.error, isLoading = false)
                }
            }
        }
    }
}
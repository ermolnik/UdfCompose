package ru.ermolnik.arch.ui.counter_vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ermolnik.arch.ui.data.CounterRepository
import javax.inject.Inject

@HiltViewModel
internal class CounterViewModel @Inject constructor(): ViewModel() {

    private val counterRepository = CounterRepository

    private val _state = MutableStateFlow(CounterState.Empty)
    val state = _state.asStateFlow()

    fun onAction(counterActions: CounterActions){
        when (counterActions){
            is CounterActions.OnIncrementClick -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(isLoading = true))
                    _state.emit(_state.value.copy(counter = counterRepository.increment(), isLoading = false))
                }
            }
            is CounterActions.OnDecrementClick -> {
                viewModelScope.launch {
                    _state.emit(_state.value.copy(isLoading = true))
                    _state.emit(_state.value.copy(counter = counterRepository.increment(), isLoading = false))
                }
            }
        }
    }
}
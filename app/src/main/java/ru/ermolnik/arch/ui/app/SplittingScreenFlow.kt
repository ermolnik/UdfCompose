package ru.ermolnik.arch.ui.app

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.ermolnik.arch.ui.counter_elm.CounterFeuture
import ru.ermolnik.arch.ui.counter_vm.CounterActions
import ru.ermolnik.arch.ui.counter_vm.CounterEffect
import ru.ermolnik.arch.ui.counter_vm.CounterScreen

internal sealed class MainScreenFlow(val route: String) {
    object CounterScreenElm : MainScreenFlow(COUNTER_SCREEN_ELM)
    object CounterScreenVM : MainScreenFlow(COUNTER_SCREEN_VM)

    companion object {
        const val COUNTER_SCREEN_ELM = "counter_screen_elm"
        const val COUNTER_SCREEN_VM = "counter_screen_vm"
    }
}

internal fun NavGraphBuilder.addMainFlow(
) {
    composable(MainScreenFlow.CounterScreenElm.route) {
        ru.ermolnik.arch.ui.counter_elm.CounterScreen(state = CounterElm.state.collectAsState()) {
            CounterElm.Reducer.reduce(it, CounterElm.state.value)
        }
    }

    composable(MainScreenFlow.CounterScreenVM.route) {
        CounterScreen(viewModel = hiltViewModel(), effects = {
            when(it){
                is CounterEffect.OpenDetailsAction -> {
                    //navigate to details
                }
            }
        })
    }
}

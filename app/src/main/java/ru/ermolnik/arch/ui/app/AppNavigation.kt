package ru.ermolnik.arch.ui.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
internal fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
//        startDestination = MainScreenFlow.CounterScreenElm.route,
        startDestination = MainScreenFlow.CounterScreenVM.route,
    ) {
        addMainFlow()
    }
}
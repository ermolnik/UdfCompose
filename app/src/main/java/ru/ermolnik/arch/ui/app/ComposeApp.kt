package ru.ermolnik.arch.ui.app

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.ermolnik.arch.ui.app.theme.ArchTheme

@Composable
internal fun ComposeApp(
) {
    ArchTheme {
        ProvideWindowInsets {
            SetStatusBarColor(color = Color.White)

            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()

            Scaffold(scaffoldState = scaffoldState) {
                AppNavigation(
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setStatusBarColor(color = color, darkIcons = useDarkIcons)
    }
}

package ru.ermolnik.arch.ui.counter_vm

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun CounterScreen(
    viewModel: CounterViewModel,
    effects: (CounterEffect) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Surface(
        color = MaterialTheme.colors.background
    ) {
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Counter: ${state.counter}",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = { viewModel.onAction(CounterActions.OnIncrementClick) }
                    ) {
                        Text(text = "Increment")
                    }
                    Button(
                        onClick = { viewModel.onAction(CounterActions.OnDecrementClick) }
                    ) {
                        Text(text = "Decrement")
                    }
                }

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), onClick = { effects(CounterEffect.OpenDetailsAction) }) {
                    Text(text = "Details")
                }

            }
        }
    }
}
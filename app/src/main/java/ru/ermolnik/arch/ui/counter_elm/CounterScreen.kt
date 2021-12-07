package ru.ermolnik.arch.ui.counter_elm

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
internal fun CounterScreen(
    state: State<CounterFeature.State>,
    listener: (CounterFeature.Event.User) -> Unit
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        when {
            state.value.isLoading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            state.value.error != null -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = state.value.error?.localizedMessage.toString(), modifier = Modifier.align(Alignment.Center))
                }
            }
            else -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Counter: ${state.value.counter}",
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
                            onClick = {
                                listener(CounterFeature.Event.User.OnIncrementClick)
                            }
                        ) {
                            Text(text = "Increment")
                        }
                        Button(
                            onClick = {
                                listener(CounterFeature.Event.User.OnDecrementClick)
                            }
                        ) {
                            Text(text = "Decrement")
                        }
                    }

                }
            }
        }
    }
}
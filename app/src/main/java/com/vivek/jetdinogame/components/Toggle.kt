package com.vivek.jetdinogame.components

import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.vivek.jetdinogame.GameState

@Composable
fun Toggle(
    modifier: Modifier,
    gameState: MutableState<GameState>,
    gameCurrentScore: MutableState<Int>,
    updateHighScore: (Int) -> Unit,
    onToggle: () -> Unit,
) {
    val state = gameState.value.isFinished() || gameState.value.isStarting()

    if (state) {
        updateHighScore(gameCurrentScore.value)

        TextButton(
            modifier = modifier,
            onClick = { onToggle() }
        ) {
            Text(
                text = when {
                    gameState.value.isStarting() -> "CLICK TO START"
                    gameState.value.isFinished() -> "GAME OVER"
                    else -> "Click to Stop"
                },
                style = MaterialTheme.typography.body1
            )
        }
    }
}
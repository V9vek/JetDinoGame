package com.vivek.jetdinogame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import com.vivek.jetdinogame.components.Toggle
import com.vivek.jetdinogame.ui.JetDinoGameTheme

class MainActivity : AppCompatActivity() {

    private val gameState: MutableState<GameState> = mutableStateOf(GameState())
    private val gameCurrentScore: MutableState<Int> = mutableStateOf(0)
    private val gameHighScore: MutableState<Int> = mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetDinoGameTheme {
                Box(modifier = Modifier) {
                    GameConsole(
                        gameState = gameState,
                        gameCurrentScore = gameCurrentScore,
                    )

                    Text(
                        text = "HI ${gameHighScore.value} ${gameCurrentScore.value}",
                        modifier = Modifier.align(Alignment.TopEnd)
                            .padding(top = 100.dp, end = 50.dp),
                        style = MaterialTheme.typography.h5
                    )

                    Toggle(
                        modifier = Modifier.fillMaxSize(),
                        gameState = gameState,
                        gameCurrentScore = gameCurrentScore,
                        updateHighScore = { score ->
                            if (score > gameHighScore.value)
                                gameHighScore.value = score
                        },
                        onToggle = { gameState.value = GameState(GameState.Status.RUNNING) }
                    )
                }
            }
        }
    }
}
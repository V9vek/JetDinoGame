package com.vivek.jetdinogame

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.res.loadImageResource
import com.vivek.jetdinogame.components.BackgroundState
import com.vivek.jetdinogame.components.CactusState
import com.vivek.jetdinogame.components.DinoState

@Composable
fun GameConsole(
    gameState: MutableState<GameState>,
    gameCurrentScore: MutableState<Int>,
//    updateHighScore: (Int) -> Unit
) {
    val dinoImage = loadImageResource(id = R.drawable.dino)
    val backgroundImage = loadImageResource(id = R.drawable.background)
    val cactusImage = loadImageResource(id = R.drawable.cactus)

    val dinoState = remember { DinoState(dinoImage) }
    val backgroundState = remember { BackgroundState(backgroundImage) }
    val cactusState = remember { CactusState(cactusImage) }
    val onClickJump = remember { mutableStateOf(false) }

    val animatedFloat = animatedFloat(initVal = 0f)
    val time = animatedFloat.value                                  // 0-1

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = { onClickJump.value = true },
                indication = null
            )
    ) {

        if (animatedFloat.isRunning && (gameState.value.isFinished() || gameState.value.isStarting())) {
            animatedFloat.stop()
        } else if (!animatedFloat.isRunning && gameState.value.isRunning()) {
            animatedFloat.snapTo(0f)
            animatedFloat.animateTo(
                targetValue = 1f,
                anim = repeatable(
                    iterations = AnimationConstants.Infinite,
                    animation = tween(
                        durationMillis = 250,
                        easing = LinearEasing
                    )
                )
            )
            gameCurrentScore.value = 0
            dinoState.init(this)
            cactusState.init(this)
        }

        if (gameState.value.isRunning()) {
            backgroundState.move()
            cactusState.move(gameCurrentScore)

            if (onClickJump.value) {
                dinoState.jump(onReachingGround = { onClickJump.value = false })
            }
        }

        backgroundState.draw(drawScope = this)
        val cactusRect = cactusState.draw(this)
        val dinoRect = dinoState.draw(drawScope = this, time = time)

        checkCollision(
            gameState,
            cactusRect,
            dinoRect
        )
    }
}

fun checkCollision(
    gameState: MutableState<GameState>,
    cactusRect: List<Rect>,
    dinoRect: Rect
) {
    if (cactusRect.firstOrNull { it.overlaps(dinoRect) } != null) {
        gameState.value = GameState(GameState.Status.FINISH)
    }
}















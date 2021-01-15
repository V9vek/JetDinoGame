package com.vivek.jetdinogame.components

import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.DeferredResource
import androidx.compose.ui.unit.*

data class CactusState(
    val image: DeferredResource<ImageAsset>
) {
    private val imageWidth = 18
    private val imageHeight = 30
    private val velocity = 11
    private val gap = 200

    private val desWidth = imageWidth * 2
    private val desHeight = imageHeight * 2

    private val cactusList: MutableList<Cactus> = emptyList<Cactus>().toMutableList()

    fun init(drawScope: DrawScope) {
        cactusList.clear()
        cactusList.add(Cactus(drawScope, currentPosX = drawScope.size.width.toInt()))
        cactusList.add(Cactus(drawScope, currentPosX = (drawScope.size.width * 1.8).toInt()))
    }

    fun move(
        gameCurrentScore: MutableState<Int>,
    ) {
        cactusList.forEach {
            it.move(velocity = velocity)
        }
        gameCurrentScore.value++
    }

    fun draw(drawScope: DrawScope): List<Rect> {
        return drawScope.drawCactus()
    }

    private fun DrawScope.drawCactus(): List<Rect> {
        val rects = emptyList<Rect>().toMutableList()

        val midY = size.height / 2

        cactusList.filter { it.currentPosX < -desWidth.dp.toIntPx() }
            .forEach { it.reset((100..200).random().dp.toIntPx() + gap.dp.toIntPx()) }

        cactusList.forEach { cactus ->
            val srcOffset = IntOffset(0, 0)
            val srcSize = IntSize(imageWidth.dp.toIntPx(), imageHeight.dp.toIntPx())
            val dstOffset = IntOffset(cactus.currentPosX, midY.toInt() - imageHeight.dp.toIntPx())
            val dstSize = IntSize(desWidth.dp.toIntPx(), desHeight.dp.toIntPx())

            image.resource.resource?.let {
                drawImage(
                    image = it,
                    srcOffset = srcOffset,
                    srcSize = srcSize,
                    dstOffset = dstOffset,
                    dstSize = dstSize
                )

                rects.add(Rect(offset = dstOffset.toOffset(), size = dstSize.toSize()))
            }

//            drawRect(
//                brush = SolidColor(Color.Red),
//                topLeft = dstOffset.toOffset(),
//                size = dstSize.toSize(),
//                style = Stroke(width = 2f)
//            )
        }

        return rects
    }

    data class Cactus(
        val drawScope: DrawScope,
        var currentPosX: Int
    ) {

        fun move(velocity: Int) {
            currentPosX -= velocity
        }

        fun reset(random: Int) {
            currentPosX = drawScope.size.width.toInt() + random
        }
    }
}



















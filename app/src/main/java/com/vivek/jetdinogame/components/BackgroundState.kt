package com.vivek.jetdinogame.components

import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.DeferredResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

data class BackgroundState(
    val image: DeferredResource<ImageAsset>
) {
    private val imageWidth = 144
    private val imageHeight = 255
    private var currentPosX = 0

    fun move() {
        currentPosX -= 2
    }

    fun draw(drawScope: DrawScope) {
        drawScope.drawBackground()
    }

    private fun DrawScope.drawBackground() {
        if (currentPosX < -size.width) {
            currentPosX = 0
        }

        image.resource.resource?.let {
            drawImage(
                image = it,
                srcOffset = IntOffset(0, 0),
                srcSize = IntSize(imageWidth.dp.toIntPx(), imageHeight.dp.toIntPx()),
                dstOffset = IntOffset(currentPosX, 0),
                dstSize = IntSize(size.width.toInt(), size.height.toInt())
            )
            drawImage(
                image = it,
                srcOffset = IntOffset(0, 0),
                srcSize = IntSize(imageWidth.dp.toIntPx(), imageHeight.dp.toIntPx()),
                dstOffset = IntOffset(size.width.toInt() + currentPosX, 0),
                dstSize = IntSize(size.width.toInt(), size.height.toInt())
            )
        }
    }
}















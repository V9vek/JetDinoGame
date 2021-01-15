package com.vivek.jetdinogame.components

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.DeferredResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toOffset

data class DinoState(
    val image: DeferredResource<ImageAsset>
) {
    private val imageWidth = 54
    private val imageHeight = 60
    private var currentPosY = 0
    private var velocity = -40
    private var gravity = 2
    private var ground = 0

    fun init(drawScope: DrawScope) {
        ground = (drawScope.size.height / 2).toInt()
    }

    fun jump(onReachingGround: () -> Unit) {
        currentPosY += velocity
        velocity += gravity

        if (currentPosY < ground && currentPosY > ((imageHeight / 2) - 10)) {
            currentPosY = 0
            velocity = -40
            gravity = 2
            onReachingGround()
        }
    }

    fun draw(drawScope: DrawScope, time: Float): Rect {
        return drawScope.drawDino(time = time)
    }

    private fun DrawScope.drawDino(time: Float): Rect {

        val midX = size.width / 2
        val midY = size.height / 2

        val center = IntOffset(
            10,
            midY.toInt() + currentPosY - (imageHeight.dp.toIntPx() / 2)
        )

        val value = if (time < 0.20) 0
        else if (time >= 0.20 && time < 0.40) 1
        else if (time >= 0.40 && time < 0.60) 2
        else if (time >= 0.60 && time < 0.80) 3
        else if (time >= 0.80 && time < 1) 4
        else 0

        image.resource.resource?.let {
            drawImage(
                image = it,
                srcOffset = IntOffset((imageWidth * value).dp.toIntPx(), 0),
                srcSize = IntSize(imageWidth.dp.toIntPx(), imageHeight.dp.toIntPx()),
                dstOffset = center,
                dstSize = IntSize(imageWidth.dp.toIntPx(), imageHeight.dp.toIntPx())
            )

//            drawRect(
//                brush = SolidColor(Color.Red),
//                topLeft = center.toOffset(),
//                size = Size(width = imageWidth.dp.toPx(), height = imageHeight.dp.toPx()),
//                style = Stroke(width = 2f)
//            )
        }

        return Rect(
            offset = center.toOffset(),
            size = Size(width = imageWidth.dp.toPx(), height = imageHeight.dp.toPx())
        )
    }
}


















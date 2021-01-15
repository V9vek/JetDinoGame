package com.vivek.jetdinogame.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(          // change for dark mode
    primary = black1,
    primaryVariant = black2,
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black
)

private val LightColorPalette = lightColors(
    primary = black1,
    primaryVariant = black2,
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black
)

@Composable
fun JetDinoGameTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
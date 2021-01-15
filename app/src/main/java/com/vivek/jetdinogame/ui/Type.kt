package com.vivek.jetdinogame.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.vivek.jetdinogame.R

val GameOverFont = fontFamily(
    font(resId = R.font.press_start, FontWeight.W400)
)

val typography = Typography(
    h5 = TextStyle(
        fontFamily = GameOverFont,
        fontWeight = FontWeight.W200,
        fontSize = 12.sp
    ),
    body1 = TextStyle(
        fontFamily = GameOverFont,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    )
)
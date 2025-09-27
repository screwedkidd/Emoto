package com.example.emoto.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.emoto.R

val HelveticaNeue = FontFamily(
    Font(R.font.helvetica_neue_light, FontWeight.Light),
    Font(R.font.helvetica_neue_roman),
    Font(R.font.helvetica_neue_bold, FontWeight.Bold)
)

val Rothek = FontFamily(
    Font(R.font.rothek_bold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Rothek,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = HelveticaNeue,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = HelveticaNeue,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontFamily = HelveticaNeue,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = HelveticaNeue,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    )
)
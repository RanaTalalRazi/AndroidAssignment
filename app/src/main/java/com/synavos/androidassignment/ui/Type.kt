package com.synavos.androidassignment.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.synavos.androidassignment.R


val font = FontFamily(
    Font(R.font.tt_common_black, weight = FontWeight.Black),
    Font(R.font.tt_common_bold, weight = FontWeight.Bold),
    Font(R.font.tt_common_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.tt_common_extra_light, weight = FontWeight.ExtraLight),
    Font(R.font.tt_common_italic, style = FontStyle.Italic),
    Font(R.font.tt_common_light, weight = FontWeight.Light),
    Font(R.font.tt_common_medium, weight = FontWeight.Medium),
    Font(R.font.tt_common_regular, weight = FontWeight.Normal),
    Font(R.font.tt_common_thin, weight = FontWeight.Thin),
    Font(R.font.tt_common_extra_bold, weight = FontWeight.ExtraBold),
    Font(R.font.tt_common_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
    Font(R.font.tt_commons_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
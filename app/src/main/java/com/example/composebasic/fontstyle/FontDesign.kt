package com.example.composebasic.fontstyle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.R

@Composable
fun FontDesign() {

    val fontFamily = FontFamily(
        Font(R.font.concert, FontWeight.Normal),
        Font(R.font.serif_bold, FontWeight.Bold),
        Font(R.font.pacifico_regular, FontWeight.Normal),
        Font(R.font.serif_semi_bold, FontWeight.SemiBold)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(20.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF1565C0), // Deep Blue
                        fontSize = 48.sp,
                    )
                ) {
                    append("J")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Green, // Light Blue
                        fontSize = 36.sp,
                    )
                ) {
                    append("etpack ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFFD81B60), // Vibrant Pinkish Red
                        fontSize = 40.sp,
                    )
                ) {
                    append("C")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Green, // Light Blue
                        fontSize = 36.sp,
                    )
                ) {
                    append("ompose")
                }
            },
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

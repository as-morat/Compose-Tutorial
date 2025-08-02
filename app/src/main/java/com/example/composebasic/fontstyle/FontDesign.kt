package com.example.composebasic.fontstyle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        colorResource(R.color.soft_blue),
                        colorResource(R.color.purple_mauve)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = colorResource(R.color.deep_purple),
                        fontSize = 58.sp,
                    )
                ) { append("J") }

                withStyle(
                    style = SpanStyle(
                        color = colorResource(R.color.cyan_deep),
                        fontSize = 28.sp,
                    )
                ) { append("etpack ") }

                withStyle(
                    style = SpanStyle(
                        color = colorResource(R.color.deep_blue),
                        fontSize = 58.sp,
                    )
                ) { append("C") }

                withStyle(
                    style = SpanStyle(
                        color = colorResource(R.color.pastel_sunset),
                        fontSize = 28.sp,
                    )
                ) { append("ompose") }
            },
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
//            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .background(Color.Transparent)
        )
    }
}

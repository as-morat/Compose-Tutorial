package com.example.composebasic.imagecard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composebasic.R

@Composable
fun ImageScreen() {

    val painter = painterResource(R.drawable.luffy)
    val description = "Monkey D. Luffy"
    val title = "Monkey D. Luffy: ‘D’ — The Will of Destiny"

    Box(
        modifier = Modifier
            .fillMaxWidth(.5f)
            .padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ImageCard(
            title,
            painter,
            description,
        )
    }
}
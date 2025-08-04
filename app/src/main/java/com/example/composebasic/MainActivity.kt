package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composebasic.animation.progressbar.CircularProgressBar
import com.example.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
//                ImageScreen()
//                FontDesign()
//                 ColorBox()
//                SnackBarText()
//                ConstraintScreen()
//                SimpleColor()
                Box (Modifier.fillMaxSize(), Alignment.Center){
                    CircularProgressBar(
                        1f,
                        100,
                    )
                }
            }
        }
    }
}


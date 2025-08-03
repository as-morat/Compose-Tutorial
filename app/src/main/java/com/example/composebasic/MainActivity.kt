package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.composebasic.animation.SimpleColor
import com.example.composebasic.constrainlayout.ConstraintScreen
import com.example.composebasic.fontstyle.FontDesign
import com.example.composebasic.imagecard.ImageScreen
import com.example.composebasic.snackbartext.SnackBarText
import com.example.composebasic.state.ColorBox
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
                SimpleColor()
            }
        }
    }
}


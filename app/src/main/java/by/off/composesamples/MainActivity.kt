package by.off.composesamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.setContent
import by.off.composesamples.ui.main.ComposeSamplesAppUI

private var snackFlag = false

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSamplesAppUI(/*onBackPressedDispatcher*/)
        }
    }
}

const val LOG = "cpmsmpllog"
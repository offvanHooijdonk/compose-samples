package by.off.composesamples.ui.customs

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.animate
import androidx.compose.animation.core.TransitionDefinition
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.transition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.navigate
import by.off.composesamples.R
import by.off.composesamples.ui.Dest

@Composable
fun FAB(visible: Boolean, iconRes: Int, onClick: () -> Unit) {
    val scale = animate(target = if (visible) 1.0f else 0.0f)

    Box(modifier = Modifier.size(56.dp), alignment = Alignment.Center) {
        FloatingActionButton(
            onClick = { onClick() },
            modifier = Modifier.drawLayer(scaleX = scale, scaleY = scale)
        ) {
            Icon(asset = vectorResource(id = iconRes))
        }
    }
}
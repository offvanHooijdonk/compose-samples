package by.off.composesamples.ui.customs

import androidx.compose.animation.core.animateAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun FAB(visible: Boolean, iconRes: Int, onClick: () -> Unit) {
    val scale = animateAsState(targetValue = if (visible) 1.0f else 0.0f).value

    Box(modifier = Modifier.size(56.dp), contentAlignment = Alignment.Center) {
        FloatingActionButton(
            onClick = { onClick() },
            modifier = Modifier.drawLayer(scaleX = scale, scaleY = scale)
        ) {
            Icon(imageVector = vectorResource(id = iconRes))
        }
    }
}
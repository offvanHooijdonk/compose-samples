package by.off.composesamples.ui.customs

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import by.off.composesamples.ui.Dest

@Composable
fun BackAppbarIcon(route: String, onClick: () -> Unit) {
    if (route != Dest.PREDICT_LIST.path) {
        Icon(imageVector = Icons.Default.ArrowBack, modifier = Modifier.clickable(indication = rememberRipple(bounded = false)) {
            onClick()
        })
    }
}
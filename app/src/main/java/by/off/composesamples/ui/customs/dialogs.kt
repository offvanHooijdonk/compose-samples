package by.off.composesamples.ui.customs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import by.off.composesamples.R

@Composable
fun SurfaceDialog(modifier: Modifier = Modifier, elevation: Dp = 0.dp, content: @Composable () -> Unit) {
    Surface(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.dialog_corner)),
        modifier = modifier,
        elevation = elevation,
        content = content
    )
}
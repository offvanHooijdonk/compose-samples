package by.off.composesamples.ui.customs

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.dimensionResource
import by.off.composesamples.R

fun Modifier.paddingActivity() =
    composed(inspectorInfo = {
        name = "padding" // todo what can I place here?
    }) {
        Modifier.padding(
            horizontal = dimensionResource(R.dimen.activity_padding_h),
            vertical = dimensionResource(R.dimen.activity_padding_v)
        )
    }
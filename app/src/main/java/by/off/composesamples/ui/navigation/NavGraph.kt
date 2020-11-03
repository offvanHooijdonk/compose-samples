package by.off.composesamples.ui.navigation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.android.parcel.Parcelize

sealed class Destination : Parcelable {
    @Parcelize
    object PredictsList : Destination()

    @Parcelize
    object PredictAdd : Destination()
}

class Actions(navigator: Navigator<Destination>) {
    val openAddDialog = {
        navigator.navigate(Destination.PredictAdd)
    }
}
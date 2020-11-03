package by.off.composesamples.ui.main

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import by.off.composesamples.PredictList
import by.off.composesamples.R
import by.off.composesamples.repo.PredictRepo
import by.off.composesamples.ui.navigation.Destination
import by.off.composesamples.ui.navigation.Navigator
import by.off.composesamples.ui.prediction.add.PredictAddDialog
import by.off.composesamples.ui.res.ComposeSamplesTheme

@Composable
fun ComposeSamplesAppUI(backDispatcher: OnBackPressedDispatcher) {
    val navigator: Navigator<Destination> = rememberSavedInstanceState(saver = Navigator.saver(backDispatcher)) {
        Navigator(Destination.PredictsList, backDispatcher)
    }

    ComposeSamplesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(color = MaterialTheme.colors.background) {

                val showDialogAdd = remember { mutableStateOf(false) }
                Scaffold(
                    topBar = { TopAppBar(title = { Text(text = "Predictions") }) },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { showDialogAdd.value = true }) {
                            Icon(asset = vectorResource(id = R.drawable.ic_add_24))
                        }
                    }
                ) {
                    Main(navigator, showDialogAdd)

                    if (showDialogAdd.value) {
                        PredictAddDialog(onDismissRequest = { showDialogAdd.value = false })
                    }

                    /*val showSnack = stateFor(*arrayOf(snackFlag)) { snackFlag }
                    if (showSnack.value) {
                        Snackbar(text = { Text(text = "Oh clicked") })
                    }*/
                }

            }

        }
    }
}

@Composable
fun Main(navigator: Navigator<Destination>, showDialogAdd: MutableState<Boolean>) { // todo better to create a View Model here (ViewModel?)
    Crossfade(current = navigator.current) { dest ->
        when (dest) {
            Destination.PredictsList -> {
                PredictList(predicts = PredictRepo.sampleData) // todo should pass a repo itself
            }
            Destination.PredictAdd -> {
                PredictAddDialog(onDismissRequest = { showDialogAdd.value = false })
            }
        }

    }
}
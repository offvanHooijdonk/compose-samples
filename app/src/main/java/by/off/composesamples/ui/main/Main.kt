package by.off.composesamples.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import by.off.composesamples.R
import by.off.composesamples.repo.PredictRepo
import by.off.composesamples.ui.Dest
import by.off.composesamples.ui.customs.FAB
import by.off.composesamples.ui.prediction.add.PredictAddDialog
import by.off.composesamples.ui.prediction.list.PredictList
import by.off.composesamples.ui.res.ComposeSamplesTheme

@ExperimentalAnimationApi
@Composable
fun ComposeSamplesAppUI() {
    val fabVisibility = remember { mutableStateOf(true) }
    val fabIconRes = remember { mutableStateOf(R.drawable.ic_add_24) }
    val showAddDialog = remember { mutableStateOf(false) }

    val navController = rememberNavController().apply {
        enableOnBackPressed(true) // TODO transition animation
    }

    ComposeSamplesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(color = MaterialTheme.colors.background) {
                Scaffold(
                    topBar = { TopAppBar(title = { Text(text = "Predictions") }) },
                    floatingActionButton = {
                        FAB(visible = fabVisibility.value, iconRes = fabIconRes.value, onClick = { showAddDialog.value = true })
                    }
                ) {
                    Main(navController) { st ->
                        st.showFAB?.let { fabVisibility.value = it }
                    }

                    if (showAddDialog.value) {
                        PredictAddDialog(onDismissRequest = { showAddDialog.value = false })
                    }
                }

            }
        }
    }
}

@Composable
fun Main(
    navController: NavHostController,
    setState: (ScreenState) -> Unit
) { // todo better to create a View Model here (ViewModel?)
    NavHost(navController = navController, startDestination = Dest.PREDICT_LIST.name) {
        composable(Dest.PREDICT_LIST.name) { PredictList(PredictRepo.sampleData, setState) } // todo arguments
    }
}

data class ScreenState(val showFAB: Boolean? = null)
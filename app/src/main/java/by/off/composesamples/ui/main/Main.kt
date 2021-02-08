package by.off.composesamples.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import by.off.composesamples.R
import by.off.composesamples.ui.NavigationMain
import by.off.composesamples.ui.customs.BackAppbarIcon
import by.off.composesamples.ui.customs.FAB
import by.off.composesamples.ui.prediction.add.PredictAddDialog
import by.off.composesamples.ui.res.ComposeSamplesTheme

@ExperimentalAnimationApi
@Composable
fun ComposeSamplesAppUI() {
    val fabVisibility = remember { mutableStateOf(true) }
    val fabIconRes = remember { mutableStateOf(R.drawable.ic_add_24) }
    val showAddDialog = remember { mutableStateOf(false) }

    val navHostController = rememberNavController().apply {
        enableOnBackPressed(true) // TODO transition animation
    }

    ComposeSamplesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(color = MaterialTheme.colors.background) {
                val currentRoute = navHostController.currentBackStackEntryAsState().value?.arguments?.getString(KEY_ROUTE) ?: ""
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Predictions") },
                            navigationIcon = { BackAppbarIcon(route = currentRoute) { navHostController.popBackStack() } } // todo custom content with animating icon visibility
                        )
                    },
                    floatingActionButton = {
                        FAB(visible = fabVisibility.value, iconRes = fabIconRes.value, onClick = { showAddDialog.value = true })
                    }
                ) {
                    Main(navHostController) { st ->
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
    navHostController: NavHostController,
    setState: (ScreenState) -> Unit
) { // todo better to create a View Model here (ViewModel?)
    NavigationMain(navHostController = navHostController, setState = setState)
}

data class ScreenState(val showFAB: Boolean? = null)
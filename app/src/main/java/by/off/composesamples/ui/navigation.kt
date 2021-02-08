package by.off.composesamples.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import by.off.composesamples.repo.PredictRepo
import by.off.composesamples.ui.main.ScreenState
import by.off.composesamples.ui.prediction.list.PredictListScreen
import by.off.composesamples.ui.prediction.single.PredictSingleScreen

@Composable
fun NavigationMain(navHostController: NavHostController, setState: (ScreenState) -> Unit) {
    NavHost(navController = navHostController, startDestination = Dest.PREDICT_LIST.path) {
        composable(Dest.PREDICT_LIST.path) { PredictListScreen(PredictRepo.sampleData, navHostController, setState) } // todo arguments
        composable(
            Dest.PREDICT_INFO.path + "{$PARAM_PREDICT_ID}"
        ) { entry -> PredictSingleScreen(entry.arg(PARAM_PREDICT_ID) ?: "") }
    }
}

/*private*/ enum class Dest(val path: String) {
    PREDICT_LIST("predict_list"),
    PREDICT_INFO("predict_info/")
}

private fun NavBackStackEntry.arg(key: String) = arguments?.getString(key)

private const val PARAM_PREDICT_ID = "predict_id"
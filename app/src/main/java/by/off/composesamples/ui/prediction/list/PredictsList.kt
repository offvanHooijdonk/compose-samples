package by.off.composesamples.ui.prediction.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import by.off.composesamples.model.Predict
import by.off.composesamples.repo.PredictRepo
import by.off.composesamples.ui.customs.paddingActivity
import by.off.composesamples.ui.main.ScreenState

@Composable
fun PredictListScreen(predicts: List<Predict>, navHostController: NavHostController, setState: (ScreenState) -> Unit) {
    setState(ScreenState(showFAB = true))
    LazyColumn(contentPadding = PaddingValues(bottom = 4.dp, top = 4.dp)) {
        item {
            Row(modifier = Modifier.paddingActivity()) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Filter here", style = TextStyle(fontSize = 18.sp))
            }
        }
        items(predicts) {
            PredictListItem(predict = it, navHostController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_PredictListScreen() {
    PredictListScreen(predicts = PredictRepo.sampleData,
        navHostController = NavHostController(AmbientContext.current),
        setState = { })
}
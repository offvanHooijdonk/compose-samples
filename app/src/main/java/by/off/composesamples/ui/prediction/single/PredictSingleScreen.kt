package by.off.composesamples.ui.prediction.single

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import by.off.composesamples.repo.PredictRepo

@Composable
fun PredictSingleScreen(predictId: String) {
    // fixme use ViewModel
    val predict = PredictRepo.sampleData.firstOrNull { it.id == predictId }

    if (predict == null) {
        Text(text = "No data for id '$predictId'")
        return
    }

    Text(text = predict.title)
}
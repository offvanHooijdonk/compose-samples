package by.off.composesamples.ui.prediction.add

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import by.off.composesamples.R
import by.off.composesamples.ui.customs.DateTimePickerDialog
import by.off.composesamples.ui.customs.InputText
import by.off.composesamples.ui.customs.SurfaceDialog
import by.off.composesamples.ui.res.defaultButtonColors
import by.off.composesamples.ui.res.defaultTextButtonColors

@Preview
@Composable
fun PreviewPredictAdd() {
    SurfaceDialog {
        PredictAddDialogLayout {}
    }
}

@Composable
fun PredictAddDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        SurfaceDialog {
            PredictAddDialogLayout(onDismissRequest)
        }
    }
}

@Composable
private fun PredictAddDialogLayout(onDismissRequest: () -> Unit) {
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val optionPos = remember { mutableStateOf("Yes") }
    val optionNeg = remember { mutableStateOf("No") }
    val showFinishesAtPicker = remember { mutableStateOf(false) }
    val showHappensOnPicker = remember { mutableStateOf(false) }


    Column(modifier = Modifier.padding(16.dp)) {
        InputText(placeholder = "Title", textVar = title)
        Spacer(modifier = Modifier.height(8.dp))
        InputText(placeholder = "Description", textVar = description)
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            InputText(modifier = Modifier.weight(1f), textVar = optionPos, placeholder = "Positive")
            Spacer(modifier = Modifier.width(16.dp))
            InputText(modifier = Modifier.weight(1f), textVar = optionNeg, placeholder = "Negative")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(R.string.label_picker_vote_finishes_at), modifier = Modifier.weight(1f))
            TextButton(onClick = { showFinishesAtPicker.value = true }, modifier = Modifier.weight(1f)) {
                Text(text = "Pick")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(R.string.label_picker_vote_happens_on), modifier = Modifier.weight(1f))
            TextButton(onClick = {}, modifier = Modifier.weight(1f)) {
                Text(text = "Pick")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onDismissRequest, colors = defaultTextButtonColors) {
                Text(text = "Cancel")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onDismissRequest, colors = defaultButtonColors) {
                Text(text = "Add")
            }
        }

        if (showFinishesAtPicker.value) DateTimePickerDialog { showFinishesAtPicker.value = false }
        if (showHappensOnPicker.value) DateTimePickerDialog { showHappensOnPicker.value = false }
    }
}

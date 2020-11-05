package by.off.composesamples.ui.prediction.add

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.ui.tooling.preview.Preview
import by.off.composesamples.ui.res.defaultButtonColors
import by.off.composesamples.ui.res.defaultTextButtonColors

@Preview
@Composable
fun PreviewPredictAdd() {
    PredictAddDialog {}
}

@Composable
fun PredictAddDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        val title = remember { mutableStateOf("") }
        val description = remember { mutableStateOf("") }
        val optionPos = remember { mutableStateOf("Yes") }
        val optionNeg = remember { mutableStateOf("No") }

        Surface(shape = RoundedCornerShape(8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                TextField(value = title.value, onValueChange = { title.value = it }, placeholder = { Text(text = "Title") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    placeholder = { Text(text = "Description") })
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextField(value = optionPos.value, onValueChange = { optionPos.value = it })
                    TextField(value = optionNeg.value, onValueChange = { optionNeg.value = it })
                }

                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = onDismissRequest, colors = defaultTextButtonColors()) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = onDismissRequest, colors = defaultButtonColors()) {
                        Text(text = "Add")
                    }
                }
            }
        }
    }
}
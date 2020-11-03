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

@Composable
fun PredictAddDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        val title = remember { mutableStateOf("") }
        val description = remember { mutableStateOf("") }

        Surface(shape = RoundedCornerShape(8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                TextField(value = title.value, onValueChange = { title.value = it }, placeholder = { Text(text = "Title") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = description.value, onValueChange = { description.value = it }, placeholder = { Text(text = "Description") })
                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = onDismissRequest, contentColor = MaterialTheme.colors.secondary) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = onDismissRequest, backgroundColor = MaterialTheme.colors.secondary) {
                        Text(text = "Add")
                    }
                }
            }
        }
    }
}
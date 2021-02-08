package by.off.composesamples.ui.customs

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun InputText(modifier: Modifier = Modifier, placeholder: String = "", textVar: MutableState<String>) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = placeholder) },
        value = textVar.value,
        onValueChange = { textVar.value = it },
        placeholder = { Text(text = placeholder) }
    )
}
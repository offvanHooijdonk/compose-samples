package by.off.composesamples.ui.customs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import by.off.composesamples.helper.currentSysInstant
import by.off.composesamples.helper.formatterDateShort
import kotlinx.datetime.Instant

@Composable
fun DateTimePicker(instant: Instant) {
    Column {
        CaptionDateValue(instant)
    }
}

@Composable
private fun CaptionDateValue(instant: Instant) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .paddingActivity()
    ) {
        Column {
            Text(formatterDateShort.format(instant.toEpochMilliseconds()), color = MaterialTheme.colors.onPrimary)
        }
    }
}

@Composable
fun DateTimePickerDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        SurfaceDialog {
            DateTimePicker(currentSysInstant)
        }
    }
}

@Preview
@Composable
private fun DateTimePickerPreview() {
    SurfaceDialog {
        DateTimePicker(currentSysInstant)
    }
}
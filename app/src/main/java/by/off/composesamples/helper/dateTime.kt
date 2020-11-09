package by.off.composesamples.helper

import android.text.format.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ContextAmbient
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

val currentSysInstant
    get() = Clock.System.now()

val currentSysDateTime
    get() = currentSysInstant.toLocalDateTime(TimeZone.currentSystemDefault())

@Composable
val formatterDateShort
    get() = SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "E MMMM dd"))
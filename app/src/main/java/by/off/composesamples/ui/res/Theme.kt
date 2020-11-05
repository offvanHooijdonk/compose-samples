package by.off.composesamples.ui.res

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ButtonConstants
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200
)

private val LightColorPalette = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200,
    onSecondary = Color.White,
    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)

@Composable
fun ComposeSamplesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun defaultButtonColors() = ButtonConstants.defaultButtonColors(backgroundColor = MaterialTheme.colors.secondary)
@Composable
fun defaultTextButtonColors() = ButtonConstants.defaultTextButtonColors(contentColor = MaterialTheme.colors.secondary)
package by.off.composesamples.ui.prediction.list

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import by.off.composesamples.LOG
import by.off.composesamples.R
import by.off.composesamples.model.Predict
import by.off.composesamples.repo.PredictRepo
import by.off.composesamples.ui.Dest
import by.off.composesamples.ui.res.*
import by.off.composesamples.ui.votesToString

@Composable
fun PredictListItem(predict: Predict, navHostController: NavHostController) {
    Card(
        elevation = 2.dp, modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = {
                navHostController.navigate(Dest.PREDICT_INFO.path + predict.id)
            })
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = dimen_item_row_padding_h,
                    vertical = dimen_item_row_padding_v
                )
                .fillMaxWidth()
        ) {
            //Row {
            Text(text = predict.title, style = MaterialTheme.typography.h6)
            //}
            Log.i(LOG, "Predict ${predict.id}")

// likes and votes row
            val liked = remember { mutableStateOf(false) }
            val likedNum = remember(liked.value) { mutableStateOf(predict.likes + if (liked.value) 1 else 0) }
            val voteState = remember { mutableStateOf(VoteState.NONE) }
            val agreedNumber =
                remember(voteState.value) { mutableStateOf(predict.votesUp + if (voteState.value == VoteState.AGREE) 1 else 0) }
            val disagreedNumber =
                remember(voteState.value) { mutableStateOf(predict.votesDown + if (voteState.value == VoteState.DISAGREE) 1 else 0) }

            Row(
                modifier = Modifier
                    .layoutId("likesBlock")
                    .wrapContentWidth()
            ) {
                PredictListItemLike(likeState = liked.value, number = likedNum.value) {
                    liked.value = !liked.value
                }
                Spacer(modifier = Modifier.width(16.dp))
                PredictListItemAgree(voteState.value, agreedNumber.value) {
                    voteState.value = if (voteState.value == VoteState.AGREE) VoteState.NONE else VoteState.AGREE
                }
                Spacer(modifier = Modifier.width(16.dp))
                PredictListItemDisagree(voteState.value, disagreedNumber.value) {
                    voteState.value = if (voteState.value == VoteState.DISAGREE) VoteState.NONE else VoteState.DISAGREE
                }
            }
        }
    }
}

/*@Composable
fun AuthorBlock(predict: Predict, modifier: Modifier = Modifier) {
    Row(modifier.padding(4.dp)) {
        Text(text = predict.authorName, overflow = TextOverflow.Ellipsis)
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            imageVector = vectorResource(R.drawable.ic_person_placeholder_24), modifier = Modifier.clip(CircleShape),
            colorFilter = ColorFilter.tint(colorSecondaryVariant)
        )
    }
}*/

@Composable
fun PredictListItemLike(likeState: Boolean, number: Long, onClick: () -> Unit) {
    ToggleCountIcon(
        icon = vectorResource(id = R.drawable.ic_fav),
        state = likeState, number = number, colorActive = color_liked
    ) { onClick() }
}

@Composable
fun PredictListItemAgree(voteState: VoteState, number: Long, onClick: () -> Unit) {
    val agreedState = voteState == VoteState.AGREE

    ToggleCountIcon(
        icon = vectorResource(id = R.drawable.ic_agree),
        state = agreedState, number = number, colorActive = color_agree
    ) { onClick() }
}

@Composable
fun PredictListItemDisagree(voteState: VoteState, number: Long, onClick: () -> Unit) {
    val stateDisagreed = voteState == VoteState.DISAGREE
    ToggleCountIcon(
        icon = vectorResource(id = R.drawable.ic_disagree),
        state = stateDisagreed, number = number, colorActive = color_disagree
    ) { onClick() }
}

@Composable
fun ToggleCountIcon(
    icon: ImageVector,
    state: Boolean,
    number: Long,
    colorActive: Color,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .padding(4.dp)
        .clickable(indication = rememberRipple(bounded = false)) {
            onClick()
        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val alpha = animateAsState(if (state) 1.0f else 0.3f).value
            val scale =
                animateAsState(if (state) 1.0f else 0.9f, SpringSpec(dampingRatio = 0.3f, stiffness = Spring.StiffnessMedium)).value
            Box(modifier = Modifier.size(26.dp), contentAlignment = Alignment.Center) {
                Image(imageVector = icon, colorFilter = ColorFilter.tint(colorActive), alpha = alpha, contentScale = FixedScale(scale))
            }
            Text(text = votesToString(number, AmbientContext.current))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSamplesTheme {
        PredictListScreen(predicts = PredictRepo.sampleData, NavHostController(AmbientContext.current)) {}
    }
}

enum class VoteState(val code: Int) {
    NONE(0), AGREE(1), DISAGREE(-1)
}
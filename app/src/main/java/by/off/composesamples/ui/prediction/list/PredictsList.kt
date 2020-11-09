package by.off.composesamples.ui.prediction.list

import android.util.Log
import androidx.compose.animation.animate
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.stateFor
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import by.off.composesamples.LOG
import by.off.composesamples.R
import by.off.composesamples.model.Predict
import by.off.composesamples.repo.PredictRepo
import by.off.composesamples.ui.main.ScreenState
import by.off.composesamples.ui.res.*
import by.off.composesamples.ui.votesToString

@Composable
fun PredictList(predicts: List<Predict>, setState: (ScreenState) -> Unit) {
    setState(ScreenState(showFAB = true))
    LazyColumnFor(contentPadding = PaddingValues(bottom = 4.dp, top = 4.dp), items = predicts) {
        PredictListItem(predict = it)
    }
}

@Composable
fun PredictListItem(predict: Predict) {
    Card(elevation = 2.dp, modifier = Modifier.padding(4.dp).clickable(onClick = {

    })) {
        Column(
            modifier = Modifier.padding(
                horizontal = dimen_item_row_padding_h,
                vertical = dimen_item_row_padding_v
            ).fillMaxWidth()
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
                stateFor(*arrayOf(voteState.value)) { predict.votesUp + if (voteState.value == VoteState.AGREE) 1 else 0 }
            val disagreedNumber =
                stateFor(*arrayOf(voteState.value)) { predict.votesDown + if (voteState.value == VoteState.DISAGREE) 1 else 0 }

            Row(modifier = Modifier.layoutId("likesBlock").wrapContentWidth()) {
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

@Composable
fun AuthorBlock(predict: Predict, modifier: Modifier = Modifier) {
    Row(modifier.padding(4.dp)) {
        Text(text = predict.authorName, overflow = TextOverflow.Ellipsis)
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            asset = vectorResource(R.drawable.ic_person_placeholder_24), modifier = Modifier.clip(CircleShape),
            colorFilter = ColorFilter.tint(colorSecondaryVariant)
        )
    }
}

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
    icon: VectorAsset,
    state: Boolean,
    number: Long,
    colorActive: Color,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.padding(4.dp).clickable(indication = RippleIndication(bounded = false)) {
        onClick()
    }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val alpha = animate(if (state) 1.0f else 0.3f)
            val scale = animate(if (state) 1.0f else 0.9f, SpringSpec(dampingRatio = 0.3f, stiffness = Spring.StiffnessMedium))
            Box(modifier = Modifier.size(26.dp), alignment = Alignment.Center) {
                Image(asset = icon, colorFilter = ColorFilter.tint(colorActive), alpha = alpha, contentScale = FixedScale(scale))
            }
            Text(text = votesToString(number, ContextAmbient.current))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSamplesTheme {
        PredictList(predicts = PredictRepo.sampleData) {}
    }
}

enum class VoteState(val code: Int) {
    NONE(0), AGREE(1), DISAGREE(-1)
}
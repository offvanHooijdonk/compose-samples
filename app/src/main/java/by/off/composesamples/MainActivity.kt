package by.off.composesamples

import android.os.Bundle
import android.util.Log
import androidx.animation.Spring
import androidx.animation.SpringSpec
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.compose.stateFor
import androidx.ui.animation.animate
import androidx.ui.core.*
import androidx.ui.foundation.*
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.gravity
import androidx.ui.material.*
import androidx.ui.material.ripple.RippleIndication
import androidx.ui.res.vectorResource
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import by.off.composesamples.model.Predict
import by.off.composesamples.repo.PredictRepo
import by.off.composesamples.ui.*

private var snackFlag = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposesamplesTheme {
                // A surface container using the 'background' color from the theme
                Stack(modifier = Modifier.fillMaxSize()) {
                    Surface(color = MaterialTheme.colors.background) {
                        Column {
                            TopAppBar(title = {
                                Text(text = "Predictions")
                            })
                            PredictList(predicts = PredictRepo.sampleData)
                            //PredictListItem(predict = PredictRepo.sampleData[0])
                        }
                    }
                    val showSnack = stateFor(snackFlag) { snackFlag }
                    if (showSnack.value) {
                        Snackbar(text = { Text(text = "Oh clicked") })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposesamplesTheme {
        PredictList(predicts = PredictRepo.sampleData)
    }
}

@Composable
fun PredictList(predicts: List<Predict>) {
    LazyColumnItems(contentPadding = InnerPadding(bottom = 4.dp, top = 4.dp), items = predicts) {
        PredictListItem(predict = it)
    }
}

@Composable
fun PredictListItem(predict: Predict) {
    Card(elevation = 2.dp, modifier = Modifier.padding(4.dp).clickable(onClick = {
        snackFlag = true
    })) {
        Column(
            modifier = Modifier.padding(
                horizontal = dimen_item_row_padding_h,
                vertical = dimen_item_row_padding_v
            ).fillMaxWidth()
        ) {
            Row {
                Text(text = predict.title, style = MaterialTheme.typography.h6)
            }
            Log.i(LOG, "Predict ${predict.id}")

// likes and votes row
            val liked = state { false }
            val likedNum = stateFor(liked.value) { predict.likes + if (liked.value) 1 else 0 }
            val voteState = state { VoteState.NONE }
            val agreedNumber = stateFor(voteState.value) { predict.votesUp + if (voteState.value == VoteState.AGREE) 1 else 0 }
            val disagreedNumber = stateFor(voteState.value) { predict.votesDown + if (voteState.value == VoteState.DISAGREE) 1 else 0 }

            Row(modifier = Modifier.layoutId("likesBlock").wrapContentWidth()) {
                PredictListItemLike(likeState = liked, number = likedNum.value)
                Spacer(modifier = Modifier.width(16.dp))
                PredictListItemAgree(voteState, agreedNumber.value)
                Spacer(modifier = Modifier.width(16.dp))
                PredictListItemDisagree(voteState, disagreedNumber.value)
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
fun PredictListItemLike(likeState: MutableState<Boolean>, number: Long) {
    val state = stateFor(likeState.value) { likeState.value }
    ToggleCountIcon(
        icon = vectorResource(id = R.drawable.ic_fav),
        state = state.value, number = number, colorActive = color_liked
    ) { newState -> likeState.value = newState }
}

@Composable
fun PredictListItemAgree(voteState: MutableState<VoteState>, number: Long) {
    val agreedState = stateFor(voteState.value) { voteState.value == VoteState.AGREE }

    ToggleCountIcon(
        icon = vectorResource(id = R.drawable.ic_agree),
        state = agreedState.value, number = number, colorActive = color_agree
    ) { newValueAgreed -> voteState.value = if (newValueAgreed) VoteState.AGREE else VoteState.NONE }
}

@Composable
fun PredictListItemDisagree(voteState: MutableState<VoteState>, number: Long) {
    val stateDisagreed = stateFor(voteState.value) { voteState.value == VoteState.DISAGREE }
    ToggleCountIcon(
        icon = vectorResource(id = R.drawable.ic_disagree),
        state = stateDisagreed.value, number = number, colorActive = color_disagree
    ) { newValueDisagreed -> voteState.value = if (newValueDisagreed) VoteState.DISAGREE else VoteState.NONE }
}

@Composable
fun ToggleCountIcon(
    icon: VectorAsset,
    state: Boolean,
    number: Long,
    colorActive: Color,
    onToggle: (Boolean) -> Unit
) {
    Row(verticalGravity = Alignment.CenterVertically) {
        Box(padding = 4.dp,
            modifier = Modifier.clickable(indication = RippleIndication(bounded = false)) {
                onToggle(!state)
            }) {
            val alpha = animate(if (state) 1.0f else 0.3f)
            val scale = animate(if (state) 1.0f else 0.9f, SpringSpec(dampingRatio = 0.3f, stiffness = Spring.StiffnessMedium))
            Box(modifier = Modifier.size(26.dp), gravity = ContentGravity.Center) {
                Image(asset = icon, colorFilter = ColorFilter.tint(colorActive), alpha = alpha, contentScale = FixedScale(scale))
            }
        }
        Text(text = votesToString(number, ContextAmbient.current))
    }
}

enum class VoteState(val code: Int) {
    NONE(0), AGREE(1), DISAGREE(-1)
}

private const val LOG = "cpmsmpllog"
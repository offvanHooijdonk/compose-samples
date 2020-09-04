package by.off.composesamples.ui

import android.content.Context
import by.off.composesamples.R
import java.math.RoundingMode
import java.text.NumberFormat

fun votesToString(votes: Long, ctx: Context): String {
    val results = convertVotesPresentation(votes)

    return results.first + when (results.second) {
        1 -> ctx.getString(R.string.votes_thousand)
        2 -> ctx.getString(R.string.votes_million)
        3 -> ctx.getString(R.string.votes_billion)
        4 -> ctx.getString(R.string.votes_trillion)
        else -> ""
    }
}

private fun convertVotesPresentation(votes: Long): Pair<String, Int> {
    var numReduced = votes
    var range = 0
    while (numReduced >= 1000) {
        numReduced /= 1000
        range++
    }

    return when (numReduced.toString().length) {
        1, 2 -> {
            NumberFormat.getInstance().apply {
                maximumFractionDigits = 1
                minimumFractionDigits = 0
                roundingMode = RoundingMode.DOWN
            }.format(votes.toDouble() / Math.pow(1000.0, range.toDouble())) to range
        }
        else -> {
            numReduced.toString() to range
        }
    }
}
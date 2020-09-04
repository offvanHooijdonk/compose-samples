package by.off.composesamples.model

data class Predict(
    val id: String,
    val title: String,
    val description: String?,
    val likes: Long,
    val votesUp: Long,
    val votesDown: Long,
    val authorName: String
)
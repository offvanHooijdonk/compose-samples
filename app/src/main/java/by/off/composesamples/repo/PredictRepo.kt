package by.off.composesamples.repo

import by.off.composesamples.model.Predict

class PredictRepo {
    companion object {
        val sampleData = listOf(
            Predict(
                id = "1",
                title = "Some predict text",
                description = "A description for a predict in place.",
                likes = 3546,
                votesUp = 8433,
                votesDown = 559,
                authorName = "John Doe"
            ),
            Predict(id = "2", title = "New predict", description = null,
                likes = 67,
                votesUp = 3755,
                votesDown = 657,
                authorName = "The Hulk"),
            Predict(id = "3", title = "Another predict", description = "Wel can see a description",
                likes = 6843,
                votesUp = 46,
                votesDown = 8432,
                authorName = "Off van Hooijdonk"),
            Predict(id = "4", title = "Yet another predict", description = null,
                likes = 84,
                votesUp = 643213,
                votesDown = 65461,
                authorName = "Luke's Father"),
            Predict(
                id = "5",
                title = "How much wood would a woodchuck chuck?",
                description = "If a woodchuck would chuck wood.",
                likes = 554,
                votesUp = 5456,
                votesDown = 5463,
                authorName = "Enola Gay"
            ),
            Predict(id = "6", title = "And a predict", description = null,
                likes = 778,
                votesUp = 684632,
                votesDown = 55,
                authorName = "John Doe"),
            Predict(id = "7", title = "Predict hah", description = null,
                likes = 66362,
                votesUp = 5654632,
                votesDown = 558,
                authorName = "John Doe"),
            Predict(id = "8", title = "Not see me predict", description = null,
                likes = 6546,
                votesUp = 46,
                votesDown = 5,
                authorName = "John Doe"),
            Predict(id = "9", title = "I am number 9", description = "And do have a description.",
                likes = 7545,
                votesUp = 51211,
                votesDown = 5336,
                authorName = "John Doe")
        )
    }
}
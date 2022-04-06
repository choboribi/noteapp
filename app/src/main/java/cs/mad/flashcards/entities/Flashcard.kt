package cs.mad.flashcards.entities

data class Flashcard(
    val term: String,
    val definition: String
) {
    companion object {
        fun getHardcodedFlashcards(): List<Flashcard> {
            val hardcoded = mutableListOf<Flashcard>()
            for (i in 1..10) {
                hardcoded.add(Flashcard("Term $i", "Definition $i"))
            }
            return hardcoded
        }
    }
}
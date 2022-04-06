package cs.mad.flashcards.entities

data class FlashcardSet(val title: String) {
    companion object {
        fun getHardcodedFlashcardSets(): List<FlashcardSet> {
            val hardcoded = mutableListOf<FlashcardSet>()
            for (i in 1..10) {
                hardcoded.add(FlashcardSet("Set $i"))
            }
            return hardcoded
        }
    }
}
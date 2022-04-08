package entities;

data class Notes(val title: String, val text: String){

    companion object {
        fun getHardcodedNotes(): List<Notes> {
            val notes = mutableListOf<Notes>()
            for (i in 1..10){
                notes.add(Notes("Title $i","Text $i"))
            }
            return notes
        }
    }
}
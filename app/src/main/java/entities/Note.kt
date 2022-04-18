package entities;

data class Note(val title: String, val text: String){

    companion object {
        fun getHardcodedNotes(): List<Note> {
            val notes = mutableListOf<Note>()
            for (i in 1..10){
                notes.add(Note("Title $i","Text $i"))
            }
            return notes
        }
    }
}
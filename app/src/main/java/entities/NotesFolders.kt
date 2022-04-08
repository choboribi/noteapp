package entities

data class NotesFolders(val title: String, val id: Long? = null){
    companion object {
        fun getHardCodedNotesFolders() : List<NotesFolders>{
            val folders = mutableListOf<NotesFolders>()
            for (i in 1..10) {
                folders.add(NotesFolders("Title $i"))
            }
            return folders
        }
    }
}
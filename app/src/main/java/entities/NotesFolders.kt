package entities

import androidx.room.*

@Entity
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

@Dao
interface FolderDao {
    @Query("select * from notesfolders")
    suspend fun getAll(): List<NotesFolders>

    @Insert
    suspend fun insert(folders: NotesFolders): Long

    @Update
    suspend fun update(folders: NotesFolders)
}
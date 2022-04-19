package entities

import androidx.room.*

@Entity
data class NotesFolders(
    var title: String,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
)

@Dao
interface FolderDao {
    @Query("select * from notesfolders")
    suspend fun getAll(): List<NotesFolders>

    @Insert
    suspend fun insert(folders: NotesFolders): Long

    @Update
    suspend fun update(folders: NotesFolders)

    @Delete
    suspend fun delete(note: NotesFolders)
}
package entities

import androidx.room.*

@Entity
data class Note(var title: String,
                var body: String,
                val parentSetId: Long,
                @PrimaryKey(autoGenerate = true) val id: Long? = null
)

@Dao
interface NoteDao {
    @Query("select * from note where parentSetId = :setId")
    suspend fun getForSet(setId: Long): List<Note>

    @Query("select * from note where id = :passedId")
    suspend fun getNote(passedId: Long): Note

    @Insert
    suspend fun insert(note: Note): Long

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
}
package com.example.notesapp.NoteDAO

import androidx.room.*
import com.example.notesapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("select * from notesTable order by id ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("Delete from notesTable")
    suspend fun deleteAllNotes()
}
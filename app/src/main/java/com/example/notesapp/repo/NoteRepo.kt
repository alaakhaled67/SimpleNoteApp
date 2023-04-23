package com.example.notesapp.repo

import com.example.notesapp.model.Note
import com.example.notesapp.NoteDAO.NotesDAO
import kotlinx.coroutines.flow.Flow

class NoteRepo(private val noteDAO: NotesDAO) {
    val allNotes: Flow<List<Note>> =noteDAO.getAllNotes()
    suspend fun insert(note: Note){
        noteDAO.insert(note)
    }
    suspend fun update(note: Note){
        noteDAO.update(note)
    }
    suspend fun delete(note: Note){
        noteDAO.delete (note)
    }
    suspend fun deleteAll(){
        noteDAO.deleteAllNotes()
    }
}
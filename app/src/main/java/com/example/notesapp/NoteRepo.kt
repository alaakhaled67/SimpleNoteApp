package com.example.notesapp

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class NoteRepo(private val noteDAO:NotesDAO) {
    val allNotes: Flow<List<Note>> =noteDAO.getAllNotes()
    suspend fun insert(note:Note){
        noteDAO.insert(note)
    }
    suspend fun update(note:Note){
        noteDAO.update(note)
    }
    suspend fun delete(note:Note){
        noteDAO.delete (note)
    }
    suspend fun deleteAll(){
        noteDAO.deleteAllNotes()
    }
}
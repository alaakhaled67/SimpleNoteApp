package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application){
    val allNotes: Flow<List<Note>>
    val repo:NoteRepo
            init{
                val dao=NoteDatabase.getDataBase(application).getNoteDao()
                repo= NoteRepo(dao)
                allNotes=repo.allNotes
            }
    fun addNote(note: Note)=viewModelScope.launch(Dispatchers.IO){repo.insert(note)}
    fun updateNote(note: Note)=viewModelScope.launch(Dispatchers.IO){repo.update(note)}
    fun deleteNote(note: Note)=viewModelScope.launch(Dispatchers.IO){repo.delete(note)}
    fun deleteAllNotes()=viewModelScope.launch(Dispatchers.IO){repo.deleteAll()}

}
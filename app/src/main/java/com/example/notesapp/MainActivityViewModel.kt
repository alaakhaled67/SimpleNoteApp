package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.Note
import com.example.notesapp.database.NoteDatabase
import com.example.notesapp.repo.NoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application):AndroidViewModel(application){
    val allNotes: Flow<List<Note>>
    val repo: NoteRepo
            init{
                val dao= NoteDatabase.getDataBase(application).getNoteDao()
                repo= NoteRepo(dao)
                allNotes=repo.allNotes
            }
    fun deleteNote(note: Note)=viewModelScope.launch(Dispatchers.IO){repo.delete(note)}
    fun deleteAllNotes()=viewModelScope.launch(Dispatchers.IO){repo.deleteAll()}

}
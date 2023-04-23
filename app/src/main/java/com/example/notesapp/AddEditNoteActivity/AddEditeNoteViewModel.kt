package com.example.notesapp.AddEditNoteActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.Note
import com.example.notesapp.repo.NoteRepo
import com.example.notesapp.database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEditeNoteViewModel(application: Application): AndroidViewModel(application){
    val repo: NoteRepo
    init{
        val dao= NoteDatabase.getDataBase(application).getNoteDao()
        repo= NoteRepo(dao)
    }
    fun addNote(note: Note)=viewModelScope.launch(Dispatchers.IO){repo.insert(note)}
    fun updateNote(note: Note)=viewModelScope.launch(Dispatchers.IO){repo.update(note)}
}
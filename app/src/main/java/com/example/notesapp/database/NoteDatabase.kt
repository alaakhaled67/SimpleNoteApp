package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.model.Note
import com.example.notesapp.NoteDAO.NotesDAO

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase(){
    abstract fun getNoteDao(): NotesDAO

    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase?=null

        fun getDataBase(context:Context): NoteDatabase {
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE =instance
                return instance
            }
        }
    }
}
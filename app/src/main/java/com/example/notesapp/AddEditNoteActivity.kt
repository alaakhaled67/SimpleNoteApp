package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityAddEditNoteBinding
import com.example.notesapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddEditNoteBinding
    lateinit var viewModel:NoteViewModel
     var noteID=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddEditNoteBinding.inflate(LayoutInflater.from(this))
        viewModel=ViewModelProvider(this).get(NoteViewModel::class.java)

        val noteType=intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle=intent.getStringExtra("noteTitle")
            val noteDescription=intent.getStringExtra("noteDescription")
            noteID=intent.getIntExtra("noteID",-1)
            binding.EditNoteTitle.setText(noteTitle)
            binding.EditNoteDescription.setText(noteDescription)
            binding.EditAddButton.setText("update note")
        }else
            binding.EditAddButton.setText("save note")

        binding.EditAddButton.setOnClickListener {
            val noteTitle=binding.EditNoteTitle.text.toString()
            val noteDescription=binding.EditNoteDescription.text.toString()

            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty()&&noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM yyyy-HH:MM")
                    val currentDate = sdf.format(Date())
                    noteID = intent.getIntExtra("noteID", -1)
                    val updateNote=Note(noteTitle,noteDescription,currentDate)
                    updateNote.id=noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
                }
            }else{
                if(noteTitle.isNotEmpty()&&noteDescription.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd MMM yyyy-HH:MM")
                val currentDate = sdf.format(Date())
                noteID = intent.getIntExtra("noteID", -1)
                viewModel.addNote(Note(noteTitle,noteDescription,currentDate))
                Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
               }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }
        setContentView(binding.root)
    }
}
package com.example.notesapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.AddEditNoteActivity.AddEditNoteActivity
import com.example.notesapp.model.Note
import com.example.notesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch

class MainActivity:AppCompatActivity(),GetDelete,GetClicked {
    lateinit var binding:ActivityMainBinding
    lateinit var viewModel:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(LayoutInflater.from(this))
        val note_adapter=note_adapter(this,this,this)
        val recyclerView=binding.RV
        recyclerView.adapter=note_adapter
        recyclerView.layoutManager=LinearLayoutManager(this)
        viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        lifecycleScope.launch{
            viewModel.allNotes.buffer().collect{ notes ->
                note_adapter.setData(notes)
            }
        }
        recyclerView.isVisible=true
        binding.FAB.setOnClickListener {
            val intent=Intent(this, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        setContentView(binding.root)
    }

    override fun onDeleteIconClicked(note: Note) {
        viewModel.deleteNote(note)
    }
    override fun onNoteClicked(note: Note) {
        val intent=Intent(this, AddEditNoteActivity::class.java)
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)
        intent.putExtra("noteType","Edit")
        startActivity(intent)
        this.finish()
    }
}
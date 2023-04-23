package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.model.Note
import com.example.notesapp.databinding.RvItemBinding

class note_adapter(val context:Context,val getClicked: GetClicked,val getDelete: GetDelete):RecyclerView.Adapter<note_adapter.ViewHolder>() {
     var notes= emptyList<Note>()
    lateinit var currentNote: Note
    private lateinit var binding:RvItemBinding
    class ViewHolder(private  val binding:RvItemBinding,private val getDelete: GetDelete):RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note){
            binding.note=note
        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        binding=RvItemBinding.inflate(LayoutInflater.from(p0.context),p0,false)
        return ViewHolder(binding,getDelete)
    }
    override fun getItemCount()=notes.size
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        currentNote=notes[p1]
        p0.bind(currentNote)
        binding.rvDelete.setOnClickListener{
            getDelete.onDeleteIconClicked(currentNote)
        }
        binding.root.setOnClickListener{
            getClicked.onNoteClicked(currentNote)
        }
    }
    fun setData(notes:List<Note>){
        this.notes=notes
        notifyDataSetChanged()
    }
}
interface GetDelete{
    fun onDeleteIconClicked(note: Note)
}
interface  GetClicked{
    fun onNoteClicked(note: Note)
}
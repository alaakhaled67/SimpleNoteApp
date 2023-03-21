package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class note_adapter(val context:Context,val getClicked: GetClicked,val getDelete: GetDelete):RecyclerView.Adapter<note_adapter.ViewHolder>() {
     var notes= emptyList<Note>()
    lateinit var currentNote:Note
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){}
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int)=ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.rv_item,p0,false))
    override fun getItemCount()=notes.size
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        currentNote=notes[p1]
        p0.itemView.findViewById<TextView>(R.id.noteTitle).text=currentNote.noteTitle
        p0.itemView.findViewById<TextView>(R.id.timeStamp).text="Last update ${currentNote.timeStamp}"
        p0.itemView.findViewById<ImageView>(R.id.rvDelete).setOnClickListener {
            getDelete.onDeleteIconClicked(currentNote)
        }
        p0.itemView.setOnClickListener {
            getClicked.onNoteClicked(currentNote)
        }
    }
    fun setData(notes:List<Note>){
        this.notes=notes
        notifyDataSetChanged()
    }
}
interface GetDelete{
    fun onDeleteIconClicked(note:Note)
}
interface  GetClicked{
    fun onNoteClicked(note:Note)
}
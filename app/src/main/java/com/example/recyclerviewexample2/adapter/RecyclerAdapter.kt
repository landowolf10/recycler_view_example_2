package com.example.recyclerviewexample2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample2.R
import com.example.recyclerviewexample2.models.NotesModel

class RecyclerAdapter(private var noteList: MutableList<NotesModel>): RecyclerView.Adapter<RecyclerAdapter.NoteHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_layout,
            parent,
            false
        )

        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.assignData(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    inner class NoteHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        fun assignData(noteList: NotesModel)
        {
            var noteTitle: TextView = itemView.findViewById(R.id.txtTitle)
            var noteContent: TextView = itemView.findViewById(R.id.txtContent)

            noteTitle.text = noteList.title
            noteContent.text = noteList.content

            itemView.setOnClickListener {
                val position: Int = adapterPosition

                Toast.makeText(itemView.context, "Note title: ${noteList.title}, Note content: " +
                        "${noteList.content}, Note position: ${position + 1}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
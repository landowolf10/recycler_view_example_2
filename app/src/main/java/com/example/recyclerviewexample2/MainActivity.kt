package com.example.recyclerviewexample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample2.adapter.RecyclerAdapter
import com.example.recyclerviewexample2.models.DataSource
import com.example.recyclerviewexample2.models.NotesModel
import com.example.recyclerviewexample2.utils.SwipeGesture
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var notes: MutableList<NotesModel> = ArrayList()
    private lateinit var noteAdapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var deletedNote: NotesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteAdapter = RecyclerAdapter(notes)
        val itemTouchHelper = ItemTouchHelper(swipeGesture)

        initRecycler(noteAdapter)
        addBtnClick(noteAdapter)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun initRecycler(noteAdapter:RecyclerAdapter)
    {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun addBtnClick(noteAdapter: RecyclerAdapter)
    {
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val editTextTitle: EditText = findViewById(R.id.etTitle)
        val editTextContent: EditText = findViewById(R.id.etContent)

        btnAdd.setOnClickListener {
            if(emptyBoxes(editTextTitle, editTextContent))
                Toast.makeText(this, "Favor de llenar todos los campos",
                    Toast.LENGTH_SHORT).show()
            else
            {
                notes.add(
                    NotesModel(
                        editTextTitle.text.toString(),
                        editTextContent.text.toString()
                    )
                )

                noteAdapter.notifyItemInserted(notes.size -1)
            }
        }
    }

    var swipeGesture = object: SwipeGesture()
    {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
        {
            var itemPosition = viewHolder.adapterPosition

            when(direction)
            {
                ItemTouchHelper.LEFT -> {
                    deletedNote = notes[itemPosition]
                    notes.removeAt(itemPosition)
                    noteAdapter.notifyItemRemoved(itemPosition)

                    Snackbar.make(recyclerView, "Note deleted", Snackbar.LENGTH_LONG).setAction("Undo", View.OnClickListener {
                        notes.add(itemPosition, deletedNote)
                        noteAdapter.notifyItemInserted(itemPosition)
                    }).show()
                }
            }
        }
    }

    private fun emptyBoxes(editTextTitle: EditText, editTextContent: EditText): Boolean
    {
        if(editTextTitle.text.toString().trim().isEmpty() || editTextContent.text.toString()
                .trim().isEmpty()
        )
            return true

        return false
    }
}
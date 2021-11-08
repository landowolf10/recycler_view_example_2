package com.example.recyclerviewexample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample2.adapter.RecyclerAdapter
import com.example.recyclerviewexample2.models.DataSource
import com.example.recyclerviewexample2.models.NotesModel

class MainActivity : AppCompatActivity() {
    private var notes: MutableList<NotesModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noteAdapter = RecyclerAdapter(notes)

        initRecycler(noteAdapter)
        addBtnClick(noteAdapter)
    }

    private fun initRecycler(noteAdapter:RecyclerAdapter)
    {
        var recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun addBtnClick(noteAdapter:RecyclerAdapter)
    {
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val ediTextTitle: EditText = findViewById(R.id.etTitle)

        btnAdd.setOnClickListener {
            notes.add(NotesModel(ediTextTitle.text.toString(), "Test 1"))
            noteAdapter.notifyItemInserted(notes.size -1)
        }
    }
}
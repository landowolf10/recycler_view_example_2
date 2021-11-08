package com.example.recyclerviewexample2.models

class DataSource
{
    fun getItemList(): MutableList<NotesModel>
    {
        var data: MutableList<NotesModel> = ArrayList()

        /*for (i in 1 ..30)
            data.add(ModelExample("Title $i", "Content $i"))*/

        data.add(NotesModel("Example title 1", "Test 1"))
        data.add(NotesModel("Example title 2", "Test 2"))
        data.add(NotesModel("Example title 3", "Test 3"))
        data.add(NotesModel("Example title 4", "Test 4"))
        data.add(NotesModel("Example title 5", "Test 5"))
        data.add(NotesModel("Example title 6", "Test 6"))
        data.add(NotesModel("Example title 7", "Test 7"))
        data.add(NotesModel("Example title 8", "Test 8"))
        data.add(NotesModel("Example title 9", "Test 9"))
        data.add(NotesModel("Example title 10", "Test 10"))

        return data
    }
}
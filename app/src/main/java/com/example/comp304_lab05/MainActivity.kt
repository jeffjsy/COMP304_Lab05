package com.example.comp304_lab05

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), LandmarksViewAdapter.ItemClickListener {
    private lateinit var adapter: LandmarksViewAdapter


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landmarks_list)

        //List of categories
        val landmarkCategories = ArrayList<String>()
        landmarkCategories.add("Attractions")
        landmarkCategories.add("Historical")
        landmarkCategories.add("Educational")

        val recyclerView: RecyclerView = findViewById(R.id.rvLandmarks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LandmarksViewAdapter(this, landmarkCategories)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, (recyclerView.layoutManager as LinearLayoutManager).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onItemClick(view: View?, position: Int) {
        val category = adapter.getItem(position)
        var intent = Intent()
        when (category) {
            "Attractions" -> intent = Intent(this, AttractionsList::class.java)
            "Historical" -> intent = Intent(this, HistoricalList::class.java)
            "Educational" -> intent = Intent(this, EducationalList::class.java)
        }
        intent.putExtra("category", category)
        startActivity(intent)
    }
}
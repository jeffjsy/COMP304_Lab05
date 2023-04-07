package com.example.comp304_lab05

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comp304_lab05.locations.ShowAttraction

class EducationalList: AppCompatActivity(), AttractionsViewAdapter.ItemClickListener {
    private lateinit var adapter: AttractionsViewAdapter
    private lateinit var attractionLoc : ArrayList<Attraction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attractions_list)

        // data to populate the RecyclerView with
        attractionLoc = ArrayList<Attraction>()
        attractionLoc.add(Attraction("Ontario Science Centre", 43.71694328740637, -79.33892658427608))
        attractionLoc.add(Attraction("Royal Ontario Museum",43.6677201963688, -79.3947803216513))
        attractionLoc.add(Attraction("Aga khan Museum", 43.72528196929622, -79.33220408982837))


        val attractionNames = ArrayList<String>()
        for (i in attractionLoc)
            attractionNames.add(i.name)

        // set up the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rvAttractions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AttractionsViewAdapter(this, attractionNames)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, (recyclerView.layoutManager as LinearLayoutManager).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onItemClick(view: View?, position: Int) {
        val attractionName = adapter.getItem(position)
        var intent = Intent()
        intent = Intent(this, ShowAttraction::class.java)
        intent.putExtra("attraction_name", attractionName).putExtra("Lat", attractionLoc[position].latitude).putExtra("Lng", attractionLoc[position].longitude)
        startActivity(intent)
    }
}
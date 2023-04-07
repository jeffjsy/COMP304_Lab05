package com.example.comp304_lab05

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comp304_lab05.locations.ShowAttraction

class HistoricalList: AppCompatActivity(), AttractionsViewAdapter.ItemClickListener {
    private lateinit var adapter: AttractionsViewAdapter
    private lateinit var attractionLoc : ArrayList<Attraction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attractions_list)

        // data to populate the RecyclerView with
        attractionLoc = ArrayList<Attraction>()
        attractionLoc.add(Attraction("Casa Loma", 43.67804848207372, -79.4094455597214))
        attractionLoc.add(Attraction("Fort York",43.639263053585495, -79.4041552257676))
        attractionLoc.add(Attraction("Black Creek Pioneer Village", 43.77355892879972, -79.51689283061341))


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
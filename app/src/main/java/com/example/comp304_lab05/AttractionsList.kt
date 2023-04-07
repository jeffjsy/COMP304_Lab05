package com.example.comp304_lab05

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comp304_lab05.locations.ShowAttraction

class AttractionsList : AppCompatActivity(), AttractionsViewAdapter.ItemClickListener {
    private lateinit var adapter: AttractionsViewAdapter
    private lateinit var attractionLoc : ArrayList<Attraction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attractions_list)

        // data to populate the RecyclerView with
        attractionLoc = ArrayList<Attraction>()
        attractionLoc.add(Attraction("CN Tower", 43.6426826393476, -79.38702461730506))
        attractionLoc.add(Attraction("Ripley's Aquarium",43.6422004779832, -79.38660118553362))
        attractionLoc.add(Attraction("Toronto Zoo", 43.820809357343585, -79.18159353448523))
        attractionLoc.add(Attraction("Canada's Wonderland", 43.84155539075336, -79.5406753540467))

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
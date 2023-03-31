package com.example.comp304_lab05

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comp304_lab05.locations.ArtGalleryOntario
import com.example.comp304_lab05.locations.CNTower
import com.example.comp304_lab05.locations.RoyalOntarioMuseum
import com.example.comp304_lab05.locations.ShowAttraction

class MainActivity : AppCompatActivity(), AttractionsViewAdapter.ItemClickListener {
    private lateinit var adapter: AttractionsViewAdapter
    private lateinit var attractionLoc : ArrayList<Attraction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data to populate the RecyclerView with
        attractionLoc = ArrayList<Attraction>()
        attractionLoc.add(Attraction("CN Tower", 43.6426826393476, -79.38702461730506))
        attractionLoc.add(Attraction("Royal Ontario Museum", 43.66774863320676, -79.39477681954116))
        attractionLoc.add(Attraction("Art Gallery of Ontario", 43.65361436259506, -79.39251028834293))
        attractionLoc.add(Attraction("Toronto Zoo", 43.820809357343585, -79.18159353448523))
        attractionLoc.add(Attraction("Casa Loma", 43.679055058968714, -79.4098671700541))

        val attractionNames = ArrayList<String>()
        for (i in attractionLoc) attractionNames.add(i.name)

        // set up the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rvAttractions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AttractionsViewAdapter(this, attractionNames)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, (recyclerView.layoutManager as LinearLayoutManager).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
    /*
    override fun onItemClick(view: View?, position: Int) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show()
    } */
    override fun onItemClick(view: View?, position: Int) {
        val attractionName = adapter.getItem(position)
        var intent = Intent()
        intent = Intent(this, ShowAttraction::class.java)
        intent.putExtra("attraction_name", attractionName).putExtra("Lat", attractionLoc[position].latitude).putExtra("Lng", attractionLoc[position].longitude)
        startActivity(intent)
    }

}
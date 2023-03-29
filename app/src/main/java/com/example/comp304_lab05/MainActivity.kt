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

class MainActivity : AppCompatActivity(), AttractionsViewAdapter.ItemClickListener {
    private lateinit var adapter: AttractionsViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data to populate the RecyclerView with
        val attractionLoc = ArrayList<String>()
        attractionLoc.add("CN Tower")
        attractionLoc.add("Royal Ontario Museum")
        attractionLoc.add("Art Gallery of Ontario")
        attractionLoc.add("Toronto Zoo")
        attractionLoc.add("Casa Loma")

        // set up the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rvAttractions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AttractionsViewAdapter(this, attractionLoc)
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
        when (attractionName){
            "CN Tower" -> {
                intent = Intent(this, CNTower::class.java)
            }
            "Royal Ontario Museum" -> {
                intent = Intent(this, RoyalOntarioMuseum::class.java)
            }
            "Art Gallery of Ontario" -> {
                intent = Intent(this, ArtGalleryOntario::class.java)
            }
        }
        intent.putExtra("attraction_name", attractionName)
        startActivity(intent)
    }

}
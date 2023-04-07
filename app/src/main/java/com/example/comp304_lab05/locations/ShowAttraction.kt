package com.example.comp304_lab05.locations;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comp304_lab05.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.comp304_lab05.databinding.ActivityMapsBinding

class ShowAttraction : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near the given location.
     * If Google Play services is not installed on the device, the user will be plocationpted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Add a marker in Toronto and move the camera
        val location = LatLng(intent.getDoubleExtra("Lat", 0.0), intent.getDoubleExtra("Lng", 0.0))
        val attractionName = intent.getStringExtra("attraction_name")
        mMap.addMarker(MarkerOptions().position(location).title(attractionName))
        setTitle(attractionName)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16.0f))
    }
}

package com.tokgozdev.samplemapsapp.views

import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.tokgozdev.samplemapsapp.R
import com.tokgozdev.samplemapsapp.model.Location
import com.tokgozdev.samplemapsapp.service.DbService
import java.lang.Exception
import java.lang.reflect.InvocationTargetException
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var dbService: DbService
    private lateinit var marker: MarkerOptions
    private lateinit var tag: String


    private companion object

    val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        loadMap()
        dbService = DbService(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.add_place_menu_item, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.action_add -> {

                val name = this.intent.getStringExtra("name")
                val country = this.intent.getStringExtra("country")
                val type = this.intent.getStringExtra("type")
                val latitude = marker.position.latitude
                val longitude = marker.position.longitude

                dbService.insertData(Location(name!!, country!!, type!!, latitude, longitude)).let { e ->
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val geoCoder = Geocoder(this@MapsActivity, Locale.getDefault())
        val location = intent.getSerializableExtra("locationModel") as? Location
        tag = intent.getStringExtra("tag")!!



        if (tag == "new") {
            println("TAG ::: NEW")
            mMap.setOnMapLongClickListener(GoogleMap.OnMapLongClickListener {

                marker = MarkerOptions().position(LatLng(it.latitude, it.longitude))

                try {
                    val address = geoCoder.getFromLocation(it.latitude, it.longitude, 1)

                    mMap.clear()
                    if (address != null) {
                        mMap.addMarker(marker)
                                .title = address[0].featureName
                    } else {
                        mMap.addMarker(marker)
                                .title = "Selected Location"
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 5f))

                } catch (e: Exception) {
                    Toast.makeText(this, "Try again", Toast.LENGTH_LONG).show()
                }


            })
        }
        if (tag == "old" && location != null) {
            println("TAG ::: OLD")

            println("Lati: ${location.latitude} Long:" + location.longitude)
            mMap.addMarker(MarkerOptions().position(LatLng(location.latitude, location.longitude)))
                    .title = location.name

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 5f))
        }


    }

    private fun loadMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }
    }


}
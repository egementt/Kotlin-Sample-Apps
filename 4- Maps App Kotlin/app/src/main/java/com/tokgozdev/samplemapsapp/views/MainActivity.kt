package com.tokgozdev.samplemapsapp.views


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View


import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity

import com.tokgozdev.samplemapsapp.R
import com.tokgozdev.samplemapsapp.model.Location
import com.tokgozdev.samplemapsapp.service.DbService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbService = DbService(this)
        val list: ArrayList<com.tokgozdev.samplemapsapp.model.Location> = dbService.readData()

        val adapter = com.tokgozdev.samplemapsapp.adapter.ListAdapter(this, list)
        list_view.adapter = adapter

        myListener(list, this)


    }


    fun buttonClick(view: View) {

        val intent = Intent(this, AddPlaceActivity::class.java)
        startActivity(intent)
        finish()

    }

    fun myListener(arrayList: ArrayList<Location>, context: Context) {
        list_view.setOnItemClickListener { parent, view, position, id
            ->
            val location: Location = arrayList.get(position)
            val intent = Intent(context, MapsActivity::class.java)
            intent.putExtra("locationModel", location)
            intent.putExtra("tag", "old")
            startActivity(intent)
        }
    }
}




















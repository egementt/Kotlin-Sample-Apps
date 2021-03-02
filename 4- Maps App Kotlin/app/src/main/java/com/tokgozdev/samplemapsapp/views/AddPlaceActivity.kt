package com.tokgozdev.samplemapsapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tokgozdev.samplemapsapp.R
import com.tokgozdev.samplemapsapp.model.Location
import kotlinx.android.synthetic.main.activity_add_place.*

class AddPlaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)


    }

    fun nextButton(view: View) {

        val name: String = nameText.text.toString()
        val country: String = countryText.text.toString()
        val type: String = typeText.text.toString()

        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("tag", "new")
        intent.putExtra("name", name)
        intent.putExtra("country", country)
        intent.putExtra("type", type)
        startActivity(intent)

    }
}
package com.tokgozdev.colorizedbackground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tokgozdev.colorizedbackground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        binding.textView.text = "Selam"


    }
}



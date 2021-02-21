package com.tokgozdev.discountapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var value = 0f;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarView()
    }


    private fun seekBarView(){

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                value = numberText.text.toString().removePrefix("$").toFloatOrNull() ?: value
                calcDiscount(progress, value)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun calcDiscount( progress: Int, price: Float) {
        val discountedPrice = price* progress
        discountText.text = "Amount(%$progress)      $${discountedPrice/100}"
        priceText.text =    "End Price               $${price-discountedPrice/100}"

    }


    fun reset(view: View){
        value = 0f
        discountText.text = "Amount(%$0.0)      $0.0"
        priceText.text =    "End Price               $0.0"
        numberText.setText("$0.0")
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            seekBar.setProgress(0, true)
        }else{
            
        }
    }



}
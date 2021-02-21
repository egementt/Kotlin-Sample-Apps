package com.tokgozdev.imageeditor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var selectedImage : Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageEdit(birghtnessSeekBar, brightnessText, "Brightness")
        imageEdit(contrastSeekBar, contrastText,"Contrast" )
        imageEdit(saturationSeekBar, saturationText,"Saturation" )

    }


     fun selectImage(view: View){

         if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
             ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)

         }else{

             val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
             startActivityForResult(galleryIntent,2)
         }
     }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if(requestCode == 1) {
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){

            val imageData = data.data

            try {
                if(Build.VERSION.SDK_INT >= 28){

                    val imageDecoder: ImageDecoder.Source = ImageDecoder.createSource(contentResolver, imageData!!)
                    selectedImage = ImageDecoder.decodeBitmap(imageDecoder)
                    imageView.setImageBitmap(selectedImage)



                }else {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.contentResolver, imageData)
                    imageView.setImageBitmap(selectedImage)
                }
            }catch (e: IOException){
                e.printStackTrace()
            }
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    fun imageEdit(seekBar: SeekBar, textView: TextView , type: String){

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = "${type} = $progress"

                if(type == "Brightness"){
                    imageView.brightness = (progress).toFloat()/100
                }
                if(type == "Contrast"){
                    imageView.contrast = (progress).toFloat()/100
                }
                if(type == "Saturation"){
                    imageView.saturation = (progress).toFloat()/100
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })




    }

    fun resetButton(view: View){
        val default: Float = 1f
        imageView.brightness = default
        imageView.saturation = default
        imageView.contrast = default

        resetSeekBar(birghtnessSeekBar, brightnessText)
        resetSeekBar(contrastSeekBar, contrastText)
        resetSeekBar(saturationSeekBar, saturationText)
    }


    fun resetSeekBar(seekBar: SeekBar, textView: TextView){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            seekBar.setProgress(100, true)
        }


    }

   /* fun changeBrightness( progress: Int) : PorterDuffColorFilter{

        if(progress >= 100)
        {
            //moving up brightness
            val value : Int = (progress-100) * 255/100
            return PorterDuffColorFilter(Color.argb(value, 255,255,255), PorterDuff.Mode.SRC_OVER)

        }else{
            //moving down brightness
            val value : Int = (100-progress) * 255/100
            return PorterDuffColorFilter(Color.argb(value, 0,0,0), PorterDuff.Mode.SRC_ATOP)
        }

    }*/


}
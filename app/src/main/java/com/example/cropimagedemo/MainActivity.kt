package com.example.cropimagedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.media.scopemediapicker.ScopedMediaPicker

class MainActivity : AppCompatActivity() {
    private val scopedMediaPicker by lazy {
        ScopedMediaPicker(
            activity = this,
            requiresCrop = false, // Optional
            allowMultipleImages = false, // Optional
        )
    }
    private val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Pick Media"
        pickMedia()
        findViewById<Button>(R.id.btnPickMedia).setOnClickListener {
          pickMedia()
        }
    }

    private fun pickMedia(){
        scopedMediaPicker.startMediaPicker(mediaType = ScopedMediaPicker.MEDIA_TYPE_IMAGE) { pathList, type ->
            when (type) {
                ScopedMediaPicker.MEDIA_TYPE_IMAGE -> {
                    // Handle your images here
                    val myIntent = Intent(this,CropImageActivity::class.java)
                    myIntent.putExtra("data",pathList.first())
                    startActivity(myIntent)

                }
                ScopedMediaPicker.MEDIA_TYPE_VIDEO -> {
                    // Handle your videos here
                }
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        scopedMediaPicker.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        scopedMediaPicker.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
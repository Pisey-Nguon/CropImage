package com.example.cropimagedemo

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class CropResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_result)
        title = "Result Cropped"
        val filename = intent.getStringExtra("data")
        findViewById<ImageView>(R.id.cropImageResult).setImageURI(Uri.parse(filename))
    }
}
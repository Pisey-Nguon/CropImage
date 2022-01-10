package com.example.cropimagedemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.cropimagedemo.databinding.ActivityCropImageBinding
import java.io.File


class CropImageActivity : AppCompatActivity() {


    private lateinit var binding:ActivityCropImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCropImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Crop Image"

        val path = intent.getStringExtra("data")
        val uri = File(path).toUri()

        /*
        We can use this property to crop image by manual
         */
//        val aspectRatio = 6f/4f
//        val bitmap = ImageUtils.croppedAsBitmap(context = this,uri = uri, aspectRatio = aspectRatio)

        binding.cropImage.setImageFilePath(path)
        binding.btnStartCrop.setOnClickListener {
            val intent = Intent(this,CropResultActivity::class.java)
            intent.putExtra("data",binding.cropImage.croppedAsFileString)
            startActivity(intent)
        }

    }
}
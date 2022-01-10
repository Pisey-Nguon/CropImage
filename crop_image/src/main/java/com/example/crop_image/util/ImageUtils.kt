package com.example.crop_image.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.crop_image.model.BitmapResource
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


object ImageUtils {

    @SuppressLint("SimpleDateFormat")
    fun bitmapToFileString(context: Context, bitmap: Bitmap): String {
        // Get the context wrapper
        val wrapper = ContextWrapper(context)

        // Initialize a new file instance to save bitmap object
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "IMG_" + "_" + SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().time) + ".jpg")

        try {
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
            bitmap.recycle()
        } catch (e: IOException) {
            e.printStackTrace()
        }

//         Return the saved bitmap uri
        return file.absoluteFile.toString()
    }



    @Throws(FileNotFoundException::class, IOException::class)
    fun croppedAsFileString(context: Context,uri: Uri, aspectRatio:Float): String {
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888
        val input: InputStream? = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions)
        input?.close()
        val height = if (bitmapOptions.outWidth > bitmapOptions.outHeight){
            bitmapOptions.outHeight / aspectRatio
        }else{
            bitmapOptions.outWidth / aspectRatio
        }
        val width = if (bitmapOptions.outWidth > bitmapOptions.outHeight){
            bitmapOptions.outHeight * aspectRatio
        }else{
            bitmapOptions.outWidth * aspectRatio
        }

        return if (height < bitmapOptions.outHeight){
            //example 6/4 or 1/1
            val bitmapResource = BitmapResource(
                bitmap!!,
                bitmapOptions.outWidth,
                height.toInt(),
                0,
                (bitmapOptions.outHeight - height).toInt() / 2
            )
            val bitmapResizing = Bitmap.createBitmap(bitmapResource.bitmap, bitmapResource.x, bitmapResource.y, bitmapResource.width, bitmapResource.height)
            bitmapToFileString(context, bitmapResizing)
        }else{
            //example 4/6
            val bitmapResource = BitmapResource(bitmap!!,width.toInt(),bitmapOptions.outHeight,(bitmapOptions.outWidth - width).toInt()/2,0)
            val bitmapResizing = Bitmap.createBitmap(bitmapResource.bitmap, bitmapResource.x, bitmapResource.y, bitmapResource.width, bitmapResource.height)
            bitmapToFileString(context, bitmapResizing)
        }
    }

    @Throws(FileNotFoundException::class, IOException::class)
    fun croppedAsBitmap(context: Context,uri: Uri, aspectRatio:Float): Bitmap {
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888
        val input: InputStream? = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions)
        input?.close()
        val height = if (bitmapOptions.outWidth > bitmapOptions.outHeight){
            bitmapOptions.outHeight / aspectRatio
        }else{
            bitmapOptions.outWidth / aspectRatio
        }
        val width = if (bitmapOptions.outWidth > bitmapOptions.outHeight){
            bitmapOptions.outHeight * aspectRatio
        }else{
            bitmapOptions.outWidth * aspectRatio
        }

        return if (height < bitmapOptions.outHeight){
            //example 6/4 or 1/1
            val bitmapResource = BitmapResource(
                bitmap!!,
                bitmapOptions.outWidth,
                height.toInt(),
                0,
                (bitmapOptions.outHeight - height).toInt() / 2
            )
            Bitmap.createBitmap(bitmapResource.bitmap, bitmapResource.x, bitmapResource.y, bitmapResource.width, bitmapResource.height)
        }else{
            //example 4/6
            val bitmapResource = BitmapResource(bitmap!!,width.toInt(),bitmapOptions.outHeight,(bitmapOptions.outWidth - width).toInt()/2,0)
            Bitmap.createBitmap(bitmapResource.bitmap, bitmapResource.x, bitmapResource.y, bitmapResource.width, bitmapResource.height)
        }
    }

}


# CropImageAndroid
[![](https://jitpack.io/v/Pisey-Nguon/CropImage.svg)](https://jitpack.io/#Pisey-Nguon/CropImage)

How to
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

*If your Gradle version below 7.0*
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' } //add this line
		}
	}

*if your Gradle version from 7.0*
Add it in your root settings.gradle at the end of repositories:

    dependencyResolutionManagement {  
      repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)  
        repositories {
	        google()  
            mavenCentral()  
            jcenter()  
            maven { url 'https://jitpack.io' }  
     }}

Step 2. Add the dependency

dependencies {

        implementation 'com.github.Pisey-Nguon:CropImage:1.0.0'

}

Step 3. Implement code

## Preview
![photo_2022-01-11 10 27 04](https://user-images.githubusercontent.com/47247206/148876293-5fed327b-b487-490d-86e7-6fc1bdfc96bf.jpeg)
![photo_2022-01-11 10 27 01](https://user-images.githubusercontent.com/47247206/148876310-fe1f643c-48b6-442f-b59a-87e28aafc673.jpeg)


    <com.example.crop_image.view.ImageCropView  
      android:id="@+id/cropImage"  
      android:layout_width="match_parent"  
      android:layout_height="match_parent"/>
**- To set image to ImageCropView**

    binding.cropImage.setImageFilePath(uri)
    binding.cropImage.setAspectRatio(1,1)

**- To get result as a file string**

    binding.btnStartCrop.setOnClickListener {  
      val result:String = binding.cropImage.croppedAsFileString
    }
    
**- To get result as a bitmap**

    binding.btnStartCrop.setOnClickListener {  
      val result:Bitmap = binding.cropImage.croppedImage
    }
    
       

**- Crop by manual as a bitmap**

    val aspectRatio = 6f/4f  
    val bitmap = ImageUtils.croppedAsBitmap(context = this,uri = uri, aspectRatio = aspectRatio)

**- Crop by manual as a file string**

    val aspectRatio = 6f/4f  
    val bitmap = ImageUtils.croppedAsFileString(context = this,uri = uri, aspectRatio = aspectRatio)

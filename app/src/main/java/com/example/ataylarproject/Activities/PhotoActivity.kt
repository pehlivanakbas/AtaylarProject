package com.example.ataylarproject.Activities

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.ataylarproject.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class PhotoActivity : AppCompatActivity() {
    private lateinit var image:ImageView
private lateinit var storage:FirebaseStorage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        storage = Firebase.storage

        //includesForCreateReference()


        val card1:CardView=findViewById(R.id.card1)
        card1.setOnClickListener {
             image=findViewById(R.id.image1)
            uploadimage(image)
        }
    }




    private fun uploadimage(image: ImageView?) {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(intent, 1)

    }

   /*private fun includesForCreateReference() {
        val storage = Firebase.storage

        var storageRef = storage.reference
        val Imagesref = storageRef.child("Companies.jpg")

// Get the data from an ImageView as bytes
        image.isDrawingCacheEnabled = true
        image.buildDrawingCache()
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = Imagesref.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }

    }


    */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if (requestCode==1){
        image.setImageURI(data?.data)
    }

    }
}
package com.example.ataylarproject.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.ataylarproject.R

class PhotoActivity : AppCompatActivity() {
    private lateinit var image:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val card1:CardView=findViewById(R.id.card1)
        card1.setOnClickListener {
             image=findViewById(R.id.image1)
            uploadimage(image)
        }
    }

    private fun uploadimage(image: ImageView?) {
        val intent=Intent()
        intent.action =Intent.ACTION_GET_CONTENT
        intent.type="image/*"
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if (requestCode==1){
        image.setImageURI(data?.data)
    }

    }
}
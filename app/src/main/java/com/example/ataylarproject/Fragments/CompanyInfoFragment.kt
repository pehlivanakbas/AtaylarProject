package com.example.at

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.ataylarproject.Activities.MainActivity
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Fragments.ProjectSitesFragment
import com.example.ataylarproject.Fragments.SiteInfoFragment
import com.example.ataylarproject.Models.Company
import com.example.ataylarproject.Network.Services.CompanyService
import com.example.ataylarproject.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import java.io.ByteArrayOutputStream


class CompanyInfoFragment(context: Context) : Fragment() {

    private lateinit var userNameEditText: EditText
    private lateinit var CompanyName: EditText
    private var companymodel: Company? = null
    private lateinit var phoneNumber: EditText
        var a: String? =null
    //  private lateinit var editTextPassword: EditText
    private lateinit var emailEditText: EditText
    private lateinit var socialMediaInstagram: EditText
    private lateinit var socialmediaTwitter: EditText
    private lateinit var socialMediaFacebook: EditText
    private lateinit var locations: EditText
    private lateinit var companyinfocompletedbutton: Button
    private lateinit var image: ImageView
    private lateinit var card2: CardView
   private lateinit var imageUri:Uri
private lateinit var companyInfoUpdateButton:Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_company_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companymodel = Company()
        findViewComponents(view)
        getCompanyInfo()
        setTextChangeListeners()
    }

    private fun findViewComponents(view: View) {


        CompanyName = view.findViewById(R.id.CompanyName)
        phoneNumber = view.findViewById(R.id.phoneNumberEditText)
        //  editTextPassword = view.findViewById(R.id.editTextPassword)
        emailEditText = view.findViewById(R.id.emailEditText)
        socialMediaInstagram = view.findViewById(R.id.socialMediaInstagram)
        socialMediaFacebook = view.findViewById(R.id.socialMediaFacebook)
        socialmediaTwitter = view.findViewById(R.id.socialMediaTwitter)
        locations = view.findViewById(R.id.locations)
        companyinfocompletedbutton = view.findViewById(R.id.companyInfoCompletedButton)
        card2 = view.findViewById(R.id.card2)
        image = view.findViewById(R.id.imagecompanylogo)
        companyInfoUpdateButton=view.findViewById(R.id.companyInfoUpdateButton)

        card2.setOnClickListener {
            uploadimage(image)
        }
        companyinfocompletedbutton.setOnClickListener { createCompanyInfo() }
        companyInfoUpdateButton.setOnClickListener { updateCompanyInfo() }

    }

    private fun updateCompanyInfo() {
        val requestBodyupdate = Company(
            companymodel!!.id, Constants.ADMIN_ID, companymodel!!.companyName,
            companymodel!!.location, companymodel!!.email, companymodel!!.phone,
            companymodel!!.twitter, companymodel!!.instagram, companymodel!!.facebook
        )

        CompanyService.updateCompany(
            requestBodyupdate,
            { success ->
                print(success)
                uploadImage()
                getCompanyInfo()
            },
            { failure ->
                print(failure)
            })
    }


    private fun createCompanyInfo() {

        val requestBody = Company(
            companymodel!!.id, Constants.ADMIN_ID, companymodel!!.companyName,
            companymodel!!.location, companymodel!!.email, companymodel!!.phone,
            companymodel!!.twitter, companymodel!!.instagram, companymodel!!.facebook
        )

        CompanyService.saveCompany(
            requestBody,
            { success ->
                print(success)

                getCompanyInfo()
            },
            { failure ->
                print(failure)
            })
    }

    private fun getCompanyInfo() {
        CompanyService.getCompanyByID(
            Constants.ADMIN_ID,
            { success ->
              success as Company
                updateUI(success)
                // foto çekmeyi çağır
                getCompanyImages()
                if(success.companyName.isEmpty()){
                    companyInfoUpdateButton.visibility=View.GONE
                }
                else {
                    companyinfocompletedbutton.visibility=View.GONE

                }

                print(success)
            },
            { failure ->
                print(failure)
            }
        )
    }

    private fun getCompanyImages() {
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference
        val pathReference = storageReference.child("company/${Constants.ADMIN_ID}.png")


// ImageView in your Activity
        val imageView = view?.findViewById<ImageView>(com.example.ataylarproject.R.id.imagecompanylogo)
        //storageReference.child("gs://ataylar-7bc6d.appspot.com/company/12.png").getBytes(Long.MAX_VALUE).addOnSuccessListener {
        pathReference.getBytes(Long.MAX_VALUE).addOnSuccessListener {
            // Use the bytes to display the image
         if (imageView != null) {
            val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)

             imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.width, image.height, false))

            /*
                Glide.with(this /* context */)
                    .load(storageReference)
                    .into(imageView)

                 */
            }
        }.addOnFailureListener {
            // Handle any errors
        }
// Download directly from StorageReference using Glide
// (See MyAppGlideModule for Loader registration)
       }

    private fun updateUI( success:Company) {

        CompanyName.setText(success.companyName)
        phoneNumber.setText(success.phone)
        emailEditText.setText(success.email)
        socialMediaInstagram.setText(success.instagram)
        socialMediaFacebook.setText(success.facebook)
        socialmediaTwitter.setText(success.twitter)
        locations.setText(success.location)
        locations.setText(success.location)


    }



    private fun uploadimage(image: ImageView) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 1)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1&& resultCode==RESULT_OK) {




            image.setImageURI(data?.data)
            imageUri= data?.data!!
            // Get the data from an ImageView as bytes
            image.isDrawingCacheEnabled = true
            image.buildDrawingCache()




        }

    }

    private fun uploadImage() {
        val storageRef = Firebase.storage.reference

// Create a reference to "mountains.jpg"

// Create a reference to 'images/mountains.jpg'
        val mountainImagesRef = storageRef.child("company/${Constants.ADMIN_ID}.png")

        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainImagesRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            var metadata = storageMetadata {
                contentType = "image/jpg"
            }  // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }


    }

    private fun setTextChangeListeners() {


        CompanyName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                companymodel?.companyName = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        phoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                companymodel?.phone = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                companymodel?.email = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        locations.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                companymodel?.location = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        socialMediaInstagram.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                companymodel?.instagram = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        socialMediaFacebook.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                companymodel?.facebook = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        socialmediaTwitter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                companymodel?.twitter = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }


    private fun replaceFragment(navDestination: NavDestination) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        when (navDestination) {
            NavDestination.SIGNUP -> fragmentManager?.replace(
                R.id.framelayout, CompanyInfoFragment(
                    MainActivity()
                )
            )
            NavDestination.LOGIN -> fragmentManager?.replace(
                R.id.framelayout,
                ProjectSitesFragment(requireContext())
            )
            NavDestination.HOMEPAGE -> fragmentManager?.replace(
                R.id.framelayout,
                ProjectSitesFragment(requireContext())
            )
            NavDestination.SITEINFO -> fragmentManager?.replace(
                R.id.framelayout,
                SiteInfoFragment(requireContext())
            )
        }
        fragmentManager?.commit()
    }

    enum class NavDestination {
        LOGIN, SIGNUP, HOMEPAGE,SITEINFO
    }
}
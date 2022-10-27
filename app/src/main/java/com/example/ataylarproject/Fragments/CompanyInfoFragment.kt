package com.example.ataylarproject.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.ataylarproject.Activities.MainActivity
import com.example.ataylarproject.R

class CompanyInfoFragment (private val mainActivity: MainActivity) : Fragment() {

    private lateinit var userNameEditText: EditText
    private lateinit var CompanyName: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var emailEditText: EditText
    private lateinit var  socialMediaInstagram: EditText
    private lateinit var  socialmediaTwitter: EditText
    private lateinit var  socialMediaFacebook: EditText
    private lateinit var companyinfocompletedbutton: Button
    private lateinit var image: ImageView
    private lateinit var card2:CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_company_info, container, false)

    }

       fun findViewComponents(view: View) {
           CompanyName = view.findViewById(R.id.CompanyName)
           phoneNumber = view.findViewById(R.id.phoneNumberEditText)
           editTextPassword = view.findViewById(R.id.editTextPassword)
           emailEditText = view.findViewById(R.id.emailEditText)
           socialMediaInstagram = view.findViewById(R.id.socialMediaInstagram)
           socialMediaFacebook = view.findViewById(R.id.socialMediaFacebook)
           socialmediaTwitter = view.findViewById(R.id.socialMediaTwitter)
           companyinfocompletedbutton=view.findViewById(R.id.companyInfoCompletedButton)
           card2=view.findViewById(R.id.card2)
           image= view.findViewById(R.id.imagecompanylogo)


        card2.setOnClickListener {
            uploadimage(image)
        }
    }
    private fun uploadimage(image: ImageView) {
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

    /*private fun setTextChangeListeners() {


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
                   CompanyName = s.toString()
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
                   phoneNumber= s.toString()
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
                    email = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
            phoneNumberEditText.addTextChangedListener(object : TextWatcher {
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
                    phoneNumber = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

        }
    }
*/


    private fun replaceFragment(navDestination: NavDestination) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        when (navDestination) {
            NavDestination.SIGNUP -> fragmentManager?.replace(R.id.framelayout,CompanyInfoFragment(
                MainActivity()
            ) )
            NavDestination.LOGIN -> fragmentManager?.replace(R.id.framelayout, ProjectSitesFragment(requireContext()))
            NavDestination.HOMEPAGE -> fragmentManager?.replace(R.id.framelayout, ProjectSitesFragment(requireContext()))
        }
        fragmentManager?.commit()
    }

enum class NavDestination{
    LOGIN,SIGNUP,HOMEPAGE
}}
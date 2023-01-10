package com.example.ataylarproject.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.at.CompanyInfoFragment
import com.example.ataylarproject.Activities.MainActivity
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.Company
import com.example.ataylarproject.Models.Region
import com.example.ataylarproject.Models.SiteInfo
import com.example.ataylarproject.Models.User
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.Network.Services.UserService
import com.example.ataylarproject.R


class SiteInfoFragment(context: Context) : Fragment() {

    private lateinit var Sitename: EditText
    private var companymodel: Company? = null
    private lateinit var phoneNumber: EditText
    var a: String? = null
    private lateinit var emailEditText: EditText
    private lateinit var locations: EditText
    private lateinit var SiteinfoComletedButton: Button
    private lateinit var image: ImageView
    private lateinit var card2: CardView
    private lateinit var imageUri: Uri
    private lateinit var SiteInfoUpdateButton: Button
    private lateinit var projectsitefragmenr: ProjectSitesFragment
    private lateinit var savesiteinfo: SiteInfo




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_site_info, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViewComponents(view)
        setTextChangeListeners()
    }

    private fun findViewComponents(view: View) {


        Sitename = view.findViewById(R.id.SiteName)
        phoneNumber = view.findViewById(R.id.phoneNumberEditText)
        emailEditText = view.findViewById(R.id.emailEditText)
        locations = view.findViewById(R.id.locations)
        SiteinfoComletedButton = view.findViewById(R.id.companyInfoCompletedButton)
        card2 = view.findViewById(R.id.card2)
        image = view.findViewById(R.id.imagecompanylogo)
        SiteInfoUpdateButton = view.findViewById(R.id.companyInfoUpdateButton)

        card2.setOnClickListener {
            uploadimage(image)
        }
        SiteinfoComletedButton.setOnClickListener {

            projectsitefragmenr= ProjectSitesFragment(requireContext())
            replaceFragment(projectsitefragmenr)

        }
        SiteInfoUpdateButton.setOnClickListener {
            createsiteinfo()
        }

    }

    private fun createsiteinfo() {


        val requestBodysiteinfo = SiteInfo(1, savesiteinfo.siteId,savesiteinfo.adress,savesiteinfo.mail,savesiteinfo.phone)


        ProjectService.saveSiteInfo(
            requestBodysiteinfo,
            { success ->
                print(success)
                updateUI(success as SiteInfo)

            },
            { failure ->
                print(failure)
            })
    }

    private fun uploadimage(image: ImageView) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 1)

    }

    private fun setTextChangeListeners() {
        Sitename.addTextChangedListener(object : TextWatcher {
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


    }

    private fun updateUI(success: SiteInfo) {

        Sitename.setText(success.siteId)
        phoneNumber.setText(success.phone)
        emailEditText.setText(success.mail)

        locations.setText(success.adress)
        locations.setText(success.id)


    }


    private fun replaceFragment(projesite: ProjectSitesFragment) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.framelayout, ProjectSitesFragment(requireContext()))
        fragmentManager?.commit()
    }
}
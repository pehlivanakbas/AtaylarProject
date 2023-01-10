package com.example.ataylarproject.Fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import com.example.at.CompanyInfoFragment
import com.example.ataylarproject.Activities.MainActivity
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.*
import com.example.ataylarproject.Network.Services.LegislationService
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import java.io.ByteArrayOutputStream


class AddFaultsFragment(context: Context,private val locationIDarg: String = ""
) : Fragment(){
    private lateinit var faultsDescription: EditText
    private lateinit var imageFaultsinfo: ImageView
    private lateinit var cardFaults: CardView
    private lateinit var faultCompletedButton: Button
    private lateinit var firstlayer: Spinner
    private lateinit var secondlayer: Spinner
    private lateinit var tirthlayer: Spinner
    private lateinit var fourthlayer: Spinner
    private lateinit var fifthlayer: Spinner
    var apiResult: MutableList<LegislationModel> = mutableListOf()
    var apiResult2: MutableList<LegislationModel> = mutableListOf()
    var apiResult3: MutableList<LegislationModel> = mutableListOf()
    var apiResult4: MutableList<LegislationModel> = mutableListOf()
    var apiResult5: MutableList<LegislationModel> = mutableListOf()
    private lateinit var imageUri: Uri
    private var faultModel: FaultModel? = FaultModel()
    var currentStufe = 1
    var upperLevelId: Int? = null
    lateinit var locationID: String
    var a: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_faults, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        faultModel = FaultModel()
        findViewComponents(view)
        faultCompletedButton.setOnClickListener {
            saveFault(faultsDescription.toString())
            replaceFragment(FaultsFragment(requireContext()))
        }
        getLegislations()
        setTextChangeListeners()
    }
    private fun updateFirstSpinner() {

        val list = mutableListOf<String>()
        apiResult.forEach { list.add(it.text) }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, list
        )
        firstlayer.adapter = adapter
        firstlayer.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                currentStufe = 2
                upperLevelId = apiResult[position].id
                getLegislations()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
    private fun updateSecondSpinner() {

        val list = mutableListOf<String>()
        apiResult2.forEach { list.add(it.text) }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, list
        )

        secondlayer.adapter = adapter


        secondlayer.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

                currentStufe = 3
                upperLevelId = apiResult2[position].id
                getLegislations()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

    }
    private fun updateThirdSpinner() {
        val list = mutableListOf<String>()
        apiResult3.forEach { list.add(it.text) }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, list
        )

        tirthlayer.adapter = adapter


        tirthlayer.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

                currentStufe = 4
                upperLevelId = apiResult3[position].id
                getLegislations()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
    private fun updateFourthSpinner() {
        val list = mutableListOf<String>()
        apiResult4.forEach { list.add(it.text) }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, list
        )

        fourthlayer.adapter = adapter


        fourthlayer.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

                currentStufe = 5
                upperLevelId = apiResult4[position].id
                getLegislations()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }
    private fun updateFifthSpinner() {
        val list = mutableListOf<String>()
        apiResult5.forEach { list.add(it.text) }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, list
        )

        fifthlayer.adapter = adapter


        fifthlayer.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

                currentStufe = 5
                upperLevelId = apiResult5[position].id
                getLegislations()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }
    private fun findViewComponents(view: View) {
        this.locationID = locationIDarg
        faultsDescription = view.findViewById(R.id.faultsDescription)
        faultCompletedButton = view.findViewById(R.id.faultCompletedButton)
        cardFaults = view.findViewById(R.id.cardFaults)
        imageFaultsinfo = view.findViewById(R.id.imageFaultsinfo)
        firstlayer = view.findViewById(R.id.firstlayer)
        secondlayer = view.findViewById(R.id.secondlayer)
        tirthlayer = view.findViewById(R.id.tirthlayer)
        fourthlayer = view.findViewById(R.id.fourthlayer)
        fifthlayer = view.findViewById(R.id.fifthlayer)

        cardFaults.setOnClickListener {
            uploadimage(imageFaultsinfo)
        }
        //   faultCompletedButton.setOnClickListener { createCompanyInfo() }
        // faultUpdateButton.setOnClickListener { updateCompanyInfo() }

    }

    private fun getLegislations() {
        LegislationService.getLegislations(currentStufe, upperLevelId ?: 0, { success ->


            when (currentStufe) {
                1 ->  {
                    apiResult = success as MutableList<LegislationModel>
                    updateFirstSpinner()
                }

                2 ->  {
                    apiResult2 = success as MutableList<LegislationModel>
                    updateSecondSpinner()
                }

                3 ->  {
                    apiResult3 = success as MutableList<LegislationModel>
                    updateThirdSpinner()
                }

                4 ->  {
                    apiResult4 = success as MutableList<LegislationModel>
                    updateFourthSpinner()
                }

                5 ->  {
                    apiResult5 = success as MutableList<LegislationModel>
                    updateFifthSpinner()
                }

            }

        }, { failure ->
            print(failure)
        })
    }
    private fun saveFault(EmployeeNote:String) {
        ProjectService.createFault(
            Fault(Constants.ADMIN_ID.toInt(), locationID, Constants.ADMIN_ID,faultModel!!.faultDescription, 5),
            { success ->
                print(success)
                uploadImage((success as Fault).id)
            },
            { failure ->
                print(failure)
            }
        )
    }
    private fun uploadimage(image: ImageView) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 1)

    } override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1&& resultCode== Activity.RESULT_OK) {
            imageFaultsinfo.setImageURI(data?.data)
            imageUri= data?.data!!
            // Get the data from an ImageView as bytes
            imageFaultsinfo.isDrawingCacheEnabled = true
            imageFaultsinfo.buildDrawingCache()
        }

    }

    private fun uploadImage(imageId: Int) {
        val storageRef = Firebase.storage.reference
        val mountainImagesRef = storageRef.child("fault/${imageId}.png")

        val bitmap = (this.imageFaultsinfo.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainImagesRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            var metadata = storageMetadata {
                contentType = "image/jpg"
            }
        }


    }

    private fun setTextChangeListeners() {
        faultsDescription.addTextChangedListener(object : TextWatcher {
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
                faultModel?.faultDescription = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
    private fun replaceFragment(fragment: FaultsFragment) {
        val fragmentManager = parentFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.framelayout, fragment)
        fragmentManager.commit()
    }
}
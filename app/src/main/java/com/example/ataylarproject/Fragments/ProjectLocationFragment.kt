package com.example.ataylarproject.Fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Path.Direction
import android.icu.text.MessagePattern.ArgType
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Adapters.LocationAdapter
import com.example.ataylarproject.Adapters.LocationListInterface
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.Location
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProjectLocationFragment(context: Context, private val regionIdArg: String = "") : Fragment(),LocationListInterface {
    lateinit var locationadapter: LocationAdapter
    lateinit var rvLocationList: RecyclerView
    lateinit var buttonback:Button
    lateinit var regionId: String
    var apiResult : MutableList<Location> = mutableListOf()
    lateinit var projectRegionFragment: ProjectRegionFragment
    lateinit var fabbuton:FloatingActionButton
    var rvItemList = mutableListOf<String>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_project_location, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.regionId = regionIdArg
        rvLocationList = view.findViewById(R.id.rvLocation)
        buttonback=view.findViewById(R.id.buttonback)
        fabbuton=view.findViewById(R.id.fabaddlocation)

        buttonback.setOnClickListener {
            projectRegionFragment= ProjectRegionFragment(requireContext(),"48")
            replaceFragment(projectRegionFragment)
        }
        fabbuton.setOnClickListener { showdialog() }
        getLocationList()
    }



    private fun showdialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Lokasyon Ekle")
        val input = EditText(requireContext())
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Lütfen lokasyon adı giriniz.")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        // Set up the buttons
        builder.setPositiveButton("Kaydet", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            createNewLocations(input.text.toString())

        })
        builder.setNegativeButton(
            "İptal",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()


    }

    private fun createNewLocations(LocationName:String) {
        ProjectService.createLocations(
            Location(LocationName, Constants.ADMIN_ID, regionId, id =0),
            { success ->
                print("oley")
                Toast.makeText(activity, "Lokasyon Ekleme Başarılı", Toast.LENGTH_LONG).show()
            },
            { failure ->
                print(failure)
                Toast.makeText(activity, "Lokasyon ekleme esnasında bir hata meydana geldi", Toast.LENGTH_LONG).show()

            }
        )
    }

    fun getLocationList() {
        ProjectService.getAllLocations(regionId,
            { success ->
                print("oley")
                apiResult = success as MutableList<Location>


                for (item in apiResult) {
                    item.name?.let { rvItemList.add(it) }
                }
                changeAdapterData(rvItemList)
                print(apiResult)
            },
            { failure ->
                print(failure)
            }
        )
    }

    private fun changeAdapterData(rvItemList: List<String>) {
        locationadapter = LocationAdapter(rvItemList, requireContext(), this)
        rvLocationList.layoutManager = LinearLayoutManager(requireContext())
        rvLocationList.adapter = locationadapter
    }

    private fun replaceFragment(fragment: ProjectRegionFragment) {
        val fragmentManager = parentFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.framelayout, fragment)
        fragmentManager.commit()
    }


    override fun transactionDetailBTPressed(index: Int) {

        Constants.lokasyonAdi = rvItemList[index]

        val secondfragment: Fragment = AssignProjectFragment(requireContext(),apiResult[index].id.toString())
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, secondfragment)
        fragmentTransaction.commit()
    }
    /*
    val username = "user123"

    val bundle = ArgType(ProjectLocationFragment).Builder(rvLocationList).build().toBundle()
    navController.navigate(R.id., bundle)


    */
    
}
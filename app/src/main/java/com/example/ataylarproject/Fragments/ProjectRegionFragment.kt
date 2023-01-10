package com.example.ataylarproject.Fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Adapters.RegionAdapter
import com.example.ataylarproject.Adapters.RegionListInterface
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.Region
import com.example.ataylarproject.Models.Site
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ProjectRegionFragment(context: Context,private val blockIdArg: String = "") : Fragment(),RegionListInterface {
    lateinit var adapter: RegionAdapter
    lateinit var rvregionlist: RecyclerView
    var apiResult : MutableList<Region> = mutableListOf()
    lateinit var blockId: String
    lateinit var projectblockFragment: ProjectBlocksFragment
    lateinit var buttonBack: AppCompatButton
    lateinit var  addregionbutton:FloatingActionButton
    var rvItemList = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view: View= inflater.inflate(R.layout.fragment_project_region, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.blockId = blockIdArg
        buttonBack = view.findViewById(R.id.buttonbackregion)
        rvregionlist = view.findViewById(R.id.rvRegion)
        addregionbutton= view.findViewById(R.id.fabaddregion)
        getRegionList()

        buttonBack.setOnClickListener {
            projectblockFragment= ProjectBlocksFragment(requireContext(), "47")

            replaceFragment(projectblockFragment)
        }

        addregionbutton.setOnClickListener {
            showdialog()
        }


    }
    private fun showdialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Bölge ekle")
        val input = EditText(requireContext())
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Lütfen bölge adı giriniz.")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        // Set up the buttons
        builder.setPositiveButton("Kaydet", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            createNewRegions(input.text.toString())

        })
        builder.setNegativeButton(
            "İptal",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()


    }

    private fun createNewRegions(RegionName:String){
        ProjectService.createRegions(
            Region(RegionName, Constants.ADMIN_ID,blockId,id=0),
            { success ->
                Toast.makeText(activity, "Bölge Ekleme Başarılı", Toast.LENGTH_LONG).show()

                getRegionList()
            })
            { failure ->
                Toast.makeText(activity, "Blok ekleme esnasında bir hata meydana geldi", Toast.LENGTH_LONG).show()
            }

    }

    fun getRegionList() {
        ProjectService.getAllRegions(
            blockId,
            { success ->
                print("oley")
                apiResult = success as MutableList<Region>


                for (item in apiResult) {
                    rvItemList.add(item.name)
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
        adapter =  RegionAdapter(rvItemList,requireContext(),this)
        rvregionlist.layoutManager = LinearLayoutManager(requireContext())
        rvregionlist.adapter = adapter
    }
    private fun replaceFragment(fragment: ProjectBlocksFragment) {
        val fragmentManager = parentFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.framelayout, fragment)
        fragmentManager.commit()
    }
    override fun transactionDetailBTPressed(index: Int) {

        Constants.bolgeAdi = rvItemList[index]


        val secondfragment:Fragment=ProjectLocationFragment(requireContext(),apiResult[index].id.toString())
        val fragmentManager: FragmentManager =requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,secondfragment)
        fragmentTransaction.commit()


        //   createNewBlocks(index)
    }


}


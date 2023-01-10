package com.example.ataylarproject.Fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
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
import com.example.ataylarproject.Adapters.BlockListInterface
import com.example.ataylarproject.Adapters.BlocksAdapter
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.Block
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ProjectBlocksFragment(context: Context, private val siteIdArg: String = "")   : androidx.fragment.app.Fragment(), BlockListInterface {

    lateinit var adapter: BlocksAdapter
    lateinit var rvblockslist: RecyclerView
    lateinit var buttonBack: Button
    lateinit var projectSitesFragment: ProjectSitesFragment
    var apiResult : MutableList<Block> = mutableListOf()
    lateinit var siteId: String
    var rvItemListBlock = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_project_blocks, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.siteId = siteIdArg
        getBlockList()
        buttonBack = view.findViewById(R.id.buttonbackblock)
        rvblockslist = view.findViewById(R.id.rvBlocks)
        val addBlockbutton:FloatingActionButton=view.findViewById(R.id.fabaddblock)

        addBlockbutton.setOnClickListener {
            showdialog()
        }
        buttonBack.setOnClickListener {
           projectSitesFragment= ProjectSitesFragment(requireContext())
           replaceFragment(projectSitesFragment)
       }
    }

    private fun showdialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Blok Ekle")
        val input = EditText(requireContext())
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Lütfen blok adı giriniz.")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("Kaydet", DialogInterface.OnClickListener { dialog, which ->
            createNewBlocks(input.text.toString())

        })
        builder.setNegativeButton(
            "İptal",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()
    }
    private fun createNewBlocks(BlockName:String) {
        ProjectService.createBlocks(
            Block(BlockName,Constants.ADMIN_ID, siteId,  id = 1),
            { success ->
                Toast.makeText(activity, "Blok Ekleme Başarılı", Toast.LENGTH_LONG).show()
                getBlockList()
            },
        ) { failure ->
            print(failure)
            Toast.makeText(activity, "Blok ekleme esnasında bir hata meydana geldi", Toast.LENGTH_LONG).show()

        }
    }

    private fun getBlockList() {
        ProjectService.getAllBlocks(
            siteId,
            { success ->
               apiResult= success as MutableList<Block>

                rvItemListBlock = mutableListOf()
                for (item in apiResult) {
                    rvItemListBlock.add(item.name)
                }

                changeAdapterData(rvItemListBlock)
                print(apiResult)

            },
            { failure ->
                print(failure)
            }
        )
    }

    private fun changeAdapterData(rvItemList: List<String>) {
        adapter = BlocksAdapter(rvItemList, requireContext(), this)
        rvblockslist.layoutManager = LinearLayoutManager(requireContext())
        rvblockslist.adapter = adapter
    }


    private fun replaceFragment(fragment: ProjectSitesFragment) {
        val fragmentManager = parentFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.framelayout, fragment)
        fragmentManager.commit()
    }

    override fun transactionbuttonRegion(index: Int) {
        Constants.blokAdi = rvItemListBlock[index]

        val regionfragment =
            ProjectRegionFragment(requireContext(),apiResult[index].id.toString())
        val fragmentManager: FragmentManager =requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, regionfragment)
        fragmentTransaction.commit()
    }


}

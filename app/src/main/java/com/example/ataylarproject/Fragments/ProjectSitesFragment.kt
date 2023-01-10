package com.example.ataylarproject.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.fragment.app.Fragment

import android.content.Context
import android.content.DialogInterface
import android.opengl.Visibility
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Adapters.ProjectsAdapter
import com.example.ataylarproject.Adapters.SiteListInterface
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.Site
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.system.measureTimeMillis
import kotlin.time.toDuration


class ProjectSitesFragment  constructor(context: Context) : Fragment(), SiteListInterface {

    lateinit var adapter: ProjectsAdapter
    lateinit var rvprojectlist: RecyclerView
    lateinit var cardView: CardView
    var apiResult: MutableList<Site> = mutableListOf()
    private lateinit var siteInfoFragment: SiteInfoFragment
    var rvItemListSite = mutableListOf<String>()
    private lateinit var progressBar:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_projects, container, false)
        // Inflate the layout for this fragment
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar=view.findViewById(R.id.progressBar)
        rvItemListSite = mutableListOf()
        rvprojectlist = view.findViewById(R.id.rv_project)
        val addsitebutton: FloatingActionButton = view.findViewById(R.id.fabaddsite)
        getSitesList()
        addsitebutton.setOnClickListener {
            showdialog()
        }

        //site ekle butonu ile createNewSiteÇağır


    }


    private fun showdialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Site Ekle")
        val input = EditText(requireContext())
        input.setHint("Lütfen site adı giriniz.")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("Kaydet", DialogInterface.OnClickListener { dialog, which ->
            createNewSite(input.text.toString())
        })
        builder.setNegativeButton(
            "İptal",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()


    }

    private fun createNewSite(siteName: String) {

        ProjectService.createSite(Site(siteName, Constants.ADMIN_ID, id = 0),
            { success ->

                Toast.makeText(activity, "Site Ekleme Başarılı", Toast.LENGTH_LONG).show()
               progressBarVisibilty()


            },
            { failure ->
                print(failure)
                Toast.makeText(activity, "Site ekleme esnasında bir hata meydana geldi", Toast.LENGTH_LONG).show()

            }
        )

    }

    private fun progressBarVisibilty(){
       progressBar.visibility = View.VISIBLE
        progressBar.visibility=View.GONE
    }
    private fun getSitesList() {
        ProjectService.getAllSites(
            { success ->
                apiResult = success as MutableList<Site>


                for (item in apiResult) {
                    rvItemListSite.add(item.name)

                }

                changeAdapterData(rvItemListSite)
            },
            { failure ->
                print(failure)
            }
        )
    }

    private fun changeAdapterData(rvItemList: List<String>) {
        adapter = ProjectsAdapter(rvItemList, requireContext(), this)
        rvprojectlist.layoutManager = LinearLayoutManager(requireContext())
        rvprojectlist.adapter = adapter
    }

    override fun transactionDetailBTPressed(index: Int) {

        Constants.siteAdi = rvItemListSite[index]

        val secondfragment: ProjectBlocksFragment =
            ProjectBlocksFragment(requireContext(),apiResult[index].id.toString())
        val fragmentManager: androidx.fragment.app.FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, secondfragment)
        fragmentTransaction.commit()
    }

    override fun projectInfoAddBtPressed(index: Int) {
        // present bottom sheet dialog
        // bottom sheet içinde 3 textfield, 1 imageview

        val secondfragment = SiteInfoFragment(requireContext())
        val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, secondfragment)
        fragmentTransaction.commit()

        print(index)
    }


    private fun replaceFragment(siteInfoFragment: SiteInfoFragment) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.framelayout, SiteInfoFragment(requireContext()))
        fragmentManager?.commit()
    }

    }



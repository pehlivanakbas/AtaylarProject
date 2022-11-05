package com.example.ataylarproject.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Adapters.ProjectsAdapter
import com.example.ataylarproject.Adapters.SiteListInterface
import com.example.ataylarproject.Models.Site
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ProjectSitesFragment(context: Context) : Fragment(),SiteListInterface{

    lateinit var adapter: ProjectsAdapter
    lateinit var rvprojectlist: RecyclerView
    lateinit var cardView: CardView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_projects, container, false)
        // Inflate the layout for this fragment
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvprojectlist = view.findViewById(R.id.rv_project)
        val addsitebutton: FloatingActionButton =view.findViewById(R.id.fabaddsite)

        getSitesList()
    //site ekle butonu ile createNewSiteÇağır
    }

    private fun createNewSite(){
        ProjectService.createSite(Site("Z sitesi", "12"),
            { success ->
                print("oley")
                getSitesList()
            },
            { failure ->
                print(failure)
            }
        )
    }

    private fun getSitesList() {
        ProjectService.getAllSites(
            { success ->
                val result = success as List<Site>

                val rvItemListSite = mutableListOf<String>()

                for (item in result) {
                    rvItemListSite.add(item.name)
                }

                changeAdapterData(rvItemListSite)
                print(result)
            },
            { failure ->
                print(failure)
            }
        )
    }

    private fun changeAdapterData(rvItemList: List<String>) {
        adapter =  ProjectsAdapter(rvItemList,requireContext(),this)
        rvprojectlist.layoutManager = LinearLayoutManager(requireContext())
        rvprojectlist.adapter = adapter
    }

    override fun transactionDetailBTPressed(index: Int) {
        val secondfragment:Fragment=ProjectBlocksFragment(requireContext())
        val fragmentManager:FragmentManager=requireActivity().supportFragmentManager
        val fragmentTransaction:FragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,secondfragment)
        fragmentTransaction.commit()


     //   createNewBlocks(index)
    }







/*
    private fun createNewBlocks(index: Int) {
        ProjectService.createBlocks(Site("Z sitesi", "12"),
            { success ->
                print("oley")
                getBlockList()
            }
        ) { failure ->
            print(failure)
        }
    }

    fun getBlockList() {
        ProjectService.getAllBlocks(
            { success ->
                print("oley")
                getBlockList()
            },
            { failure ->
                print(failure)
            }
        )
    }

    */

    //
    /*
   private fun createNewRegions(){
       ProjectService.createRegions(Site("Z sitesi", "12"),
           { success ->
               print("oley")
               getRegionList()
           },
           { failure ->
               print(failure)
           }
       )
   }

   fun getRegionList() {
       ProjectService.getAllRegions(
           { success ->
               print("oley")
               getRegionList()
           },
           { failure ->
               print(failure)
           }
       )
   }
   //
   private fun createNewLocations(){
       ProjectService.createLocations(Site("Z sitesi", "12"),
           { success ->
               print("oley")
               getLocationList()
           },
           { failure ->
               print(failure)
           }
       )
   }

   fun getLocationList() {
       ProjectService.getAllRegions(
           { success ->
               print("oley")
               getLocationList()
           },
           { failure ->
               print(failure)
           }
       )
   }
   //

    */
}
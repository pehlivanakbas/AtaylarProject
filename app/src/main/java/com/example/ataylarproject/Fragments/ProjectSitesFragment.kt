package com.example.ataylarproject.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Adapters.EmployeeListAdapter
import com.example.ataylarproject.Adapters.ProjectsAdapter
import com.example.ataylarproject.Models.Site
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R
import com.google.android.material.snackbar.Snackbar


class ProjectSitesFragment  (context: Context) : Fragment(){

    lateinit var adapter: ProjectsAdapter
    lateinit var rvprojectlist: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_projects, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvprojectlist = view.findViewById(R.id.rv_project)
        val addsitebutton: View =view.findViewById(R.id.fabaddsite)



        addsitebutton.setOnClickListener {
                view ->
            Snackbar.make(view, "Kayıt ekleme işlemi için yeni bir sayfa açılacaktır", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }

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

                val rvItemList = mutableListOf<String>()

                for (item in result) {
                    rvItemList.add(item.name)
                }

                changeAdapterData(rvItemList)
                print(result)
            },
            { failure ->
                print(failure)
            }
        )
    }

    private fun changeAdapterData(rvItemList: List<String>) {
        adapter =  ProjectsAdapter(rvItemList)
        rvprojectlist.layoutManager = LinearLayoutManager(requireContext())
        rvprojectlist.adapter = adapter
    }



    /*
    //
    private fun createNewBlocks(){
        ProjectService.createBlocks(Site("Z sitesi", "12"),
            { success ->
                print("oley")
                getBlockList()
            },
            { failure ->
                print(failure)
            }
        )
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
    //
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
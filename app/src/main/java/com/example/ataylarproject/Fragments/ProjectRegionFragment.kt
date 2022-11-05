package com.example.ataylarproject.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Adapters.RegionAdapter
import com.example.ataylarproject.Adapters.RegionListInterface
import com.example.ataylarproject.Models.Region
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R


class ProjectRegionFragment(context: Context) : Fragment(),RegionListInterface {
    lateinit var adapter: RegionAdapter
    lateinit var rvregionlist: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view: View= inflater.inflate(R.layout.fragment_project_region, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvregionlist = view.findViewById(R.id.rvRegion)

        getRegionList()
    }

    private fun createNewRegions(){
        ProjectService.createRegions(
            Region("Z sitesi", "12","11"),
            { success ->
                print("oley")
                getRegionList()
            })
            { failure ->
                print(failure)
            }

    }

    fun getRegionList() {
        ProjectService.getAllRegions(
            { success ->
                print("oley")
                val result = success as List<Region>

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
        adapter =  RegionAdapter(rvItemList,requireContext(),this)
        rvregionlist.layoutManager = LinearLayoutManager(requireContext())
        rvregionlist.adapter = adapter
    }
    override fun transactionDetailBTPressed(index: Int) {
        val secondfragment:Fragment=ProjectLocationFragment(requireContext())
        val fragmentManager: FragmentManager =requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,secondfragment)
        fragmentTransaction.commit()


        //   createNewBlocks(index)
    }


}


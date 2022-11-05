package com.example.ataylarproject.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Activities.PhotoActivity
import com.example.ataylarproject.Adapters.LocationAdapter
import com.example.ataylarproject.Adapters.LocationListInterface
import com.example.ataylarproject.Adapters.ProjectsAdapter
import com.example.ataylarproject.Models.Location
import com.example.ataylarproject.Models.Region
import com.example.ataylarproject.Models.Site
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R

class ProjectLocationFragment(context: Context) : Fragment(),LocationListInterface {
    lateinit var locationadapter: LocationAdapter
    lateinit var rvLocationList: RecyclerView
    lateinit var buttonNext: Button
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
        rvLocationList = view.findViewById(R.id.rvLocation)
        buttonNext=view.findViewById(R.id.buttonnext)
        buttonNext.setOnClickListener {
            activity?.let{
                val intent = Intent (it, PhotoActivity::class.java)
                it.startActivity(intent)
            }
        }
        getLocationList()
    }

    private fun createNewLocations() {
        ProjectService.createLocations(
            Location("Z sitesi", "12", "19"),
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
        ProjectService.getAllLocations(
            { success ->
                print("oley")
                val result = success as List<Location>

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
        locationadapter = LocationAdapter(rvItemList, requireContext(), this)
        rvLocationList.layoutManager = LinearLayoutManager(requireContext())
        rvLocationList.adapter = locationadapter
    }

    override fun transactionDetailBTPressed(index: Int) {
        val secondfragment: Fragment = FaultsFragment(requireContext())
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, secondfragment)
        fragmentTransaction.commit()
    }
}
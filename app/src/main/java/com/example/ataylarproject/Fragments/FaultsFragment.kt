package com.example.ataylarproject.Fragments

import android.content.Context
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
import com.example.ataylarproject.Adapters.BlocksAdapter
import com.example.ataylarproject.Adapters.FaultListInterface
import com.example.ataylarproject.Adapters.FaultsAdapter
import com.example.ataylarproject.Models.Fault
import com.example.ataylarproject.Models.Location
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R

class FaultsFragment(context: Context) : Fragment(),FaultListInterface {
    lateinit var adapter: FaultsAdapter
    lateinit var rvfaultslist: RecyclerView
    lateinit var buttonBack: Button
    lateinit var buttonnext: Button
    lateinit var projectregion: ProjectRegionFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_faults, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonBack = view.findViewById(R.id.buttonbackfault)
        rvfaultslist = view.findViewById(R.id.rvFaults)
        getFault()

    }


        private fun createnewFault() {
            ProjectService.createFault(
                Fault("Mutfak arızalı", "12", "19"),
                { success ->
                    print("oley")
                    getFault()
                },
                { failure ->
                    print(failure)
                }
            )
        }

        fun getFault() {
            ProjectService.getAllFaults(
                { success ->
                    print("oley")
                    val result = success as List<Fault>

                    val rvItemList = mutableListOf<String>()

                    for (item in result) {
                        rvItemList.add(item.employeeNote)
                    }
                 changeAdapterData(rvItemList)
                    print(result)
                },
                { failure ->
                    print(failure)
                }
            )
        }

        fun changeAdapterData(rvItemList: List<String>) {
            adapter = FaultsAdapter(rvItemList, requireContext(), this)
            rvfaultslist.layoutManager = LinearLayoutManager(requireContext())
            rvfaultslist.adapter = adapter
        }

    override fun transactionbuttonFault(position: Int) {
        val faultfragment:Fragment=ProjectSitesFragment(requireContext())
        val fragmentManager: FragmentManager = this.requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,faultfragment)
        fragmentTransaction.commit()
    }


}


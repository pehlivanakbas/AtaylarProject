package com.example.ataylarproject.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Activities.PhotoActivity
import com.example.ataylarproject.Adapters.AllUsersAdapter
import com.example.ataylarproject.Adapters.FaultListInterface
import com.example.ataylarproject.Adapters.FaultsAdapter
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.Fault
import com.example.ataylarproject.Models.User
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.Network.Services.UserService
import com.example.ataylarproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FaultsFragment(context: Context ) : Fragment(), FaultListInterface {
    lateinit var adapter: FaultsAdapter
    lateinit var rvfaultslist: RecyclerView
    var apiResultFaults: MutableList<Fault> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_faults, container, false)
        return view
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvfaultslist = view.findViewById(R.id.rvFaults)
        allgetFaults()
        changeAdapterDataFaults(apiResultFaults)

    }

    private fun allgetFaults() {
        ProjectService.getALlFaults(
            { success ->
                apiResultFaults= success as MutableList<Fault>

                val rvfaultslist = mutableListOf<String>()
                for (item in apiResultFaults) {
                    rvfaultslist.add(item.employeeNote)
                }
                changeAdapterDataFaults(apiResultFaults)
                          },
            { failure ->
                print(failure)
            }
        )
    }
private fun changeAdapterDataFaults(rvItemList: MutableList<Fault>) {
    adapter = FaultsAdapter(rvItemList,requireContext())
    rvfaultslist.layoutManager = LinearLayoutManager(requireContext())
    rvfaultslist.adapter = adapter
}

    override fun transactionbuttonFault(index: Int) {
        val faultfragment: Fragment = AddFaultsFragment(requireContext())
        val fragmentManager: FragmentManager = this.requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, faultfragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: ProjectLocationFragment) {
        val fragmentManager = parentFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.framelayout, fragment)
        fragmentManager.commit()
    }


}


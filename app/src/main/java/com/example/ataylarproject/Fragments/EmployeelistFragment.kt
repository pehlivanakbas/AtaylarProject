package com.example.ataylarproject.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Adapters.EmployeeListAdapter
import com.example.ataylarproject.Data.DataEmployee
import com.example.ataylarproject.Models.User
import com.example.ataylarproject.Network.Services.UserService
import com.example.ataylarproject.R


class EmployeelistFragment(context: Context) : Fragment() {


    private lateinit var adapter: EmployeeListAdapter
    lateinit var rvEmployeelist: RecyclerView
    private lateinit var rvArrayList: ArrayList<DataEmployee>
    private lateinit var addEmployeeFragment: AddEmployeeFragment
    var apiResult: MutableList<User> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employeelist, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchUsers()

        rvEmployeelist = view.findViewById(R.id.rv_employee)
        val addemployeebutton: View = view.findViewById(R.id.fab)

        addemployeebutton.setOnClickListener {
            addEmployeeFragment= AddEmployeeFragment()
            replaceFragment(addEmployeeFragment)
        }
    }


    private fun fetchUsers() {
        UserService.getUsers(
            { success ->
                apiResult = success as MutableList<User>
                changeAdapterData(apiResult)
                print(apiResult)
            },
            { failure ->
                print(failure)
            }
        )
    }

    private fun changeAdapterData(rvItemList: MutableList<User>) {
        adapter = EmployeeListAdapter(rvItemList, this@EmployeelistFragment)
        rvEmployeelist.layoutManager = LinearLayoutManager(requireContext())
        rvEmployeelist.adapter = adapter
    }

    private fun replaceFragment(addEmployeeFragment: AddEmployeeFragment) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.framelayout, AddEmployeeFragment())
        fragmentManager?.commit()
    }





    }














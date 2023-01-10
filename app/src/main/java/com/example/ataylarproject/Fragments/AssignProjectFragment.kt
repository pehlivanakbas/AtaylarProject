package com.example.ataylarproject.Fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Adapters.AllUsersAdapter
import com.example.ataylarproject.Adapters.FaultsAdapter
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.Fault
import com.example.ataylarproject.Models.User
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.Network.Services.UserService
import com.example.ataylarproject.R


class AssignProjectFragment(context: Context, private val locationIDarg: String = ""
): Fragment()


{
    lateinit var rvAllUsers: RecyclerView
    lateinit var rvfaultslist: RecyclerView
    lateinit var siteinfocard:TextView
    lateinit var blockinfocard:TextView
    lateinit var regioninfocard:TextView
    lateinit var locationinfocard:TextView
    lateinit var assignedpersonel:TextView
    lateinit var sitenameassign:TextView
    lateinit var blocknameassign:TextView
    lateinit var regionassign:TextView
    lateinit var locationassign:TextView
    lateinit var personelinfo:TextView
    lateinit var personelname:TextView
    lateinit var personeljob:TextView
    lateinit var personelphonennumber:TextView
    lateinit var personeltcno:TextView
    lateinit var ssknopersonel:TextView
    lateinit var personelmeslekodası:TextView
    lateinit var rolepersonel:TextView
    lateinit var ownpersonelcheck:TextView
    lateinit var tablelayoutpersonelinfo:TableLayout
    lateinit var acceptbuttonforfaults:Button
    lateinit var saveAssignedPersonel:Button
    private lateinit var adapterUser: AllUsersAdapter
    private lateinit var adapterFault: FaultsAdapter
    lateinit var locationID: String
    private lateinit var addFaultsFragment: AddFaultsFragment





    var apiResultUser: MutableList<User> = mutableListOf()
    var apiResultFault: MutableList<Fault> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view=inflater.inflate(R.layout.fragment_assign_project, container, false)
    return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.locationID = locationIDarg
        rvAllUsers=view.findViewById(R.id.chooseAssign)
        siteinfocard=view.findViewById(R.id.siteinfocard)
        siteinfocard.text=Constants.siteAdi
        blockinfocard=view.findViewById(R.id.blockinfocard)
        blockinfocard.text=Constants.blokAdi
        regioninfocard=view.findViewById(R.id.regioninfocard)
        regioninfocard.text=Constants.bolgeAdi
        locationinfocard=view.findViewById(R.id.locationinfocard)
        locationinfocard.text=Constants.lokasyonAdi
        acceptbuttonforfaults=view.findViewById(R.id.acceptbuttonforfaults)
        saveAssignedPersonel=view.findViewById(R.id.saveAssignedPersonel)
        sitenameassign=view.findViewById(R.id.sitenameassign)
        blocknameassign=view.findViewById(R.id.blocknameassign)
        regionassign=view.findViewById(R.id.regionassign)
        locationassign=view.findViewById(R.id.locationassign)
        personelname=view.findViewById(R.id.personelname)
        personeljob=view.findViewById(R.id.personeljob)
        personelphonennumber=view.findViewById(R.id.personelphonennumber)
        personeltcno=view.findViewById(R.id.personeltcno)
        ssknopersonel=view.findViewById(R.id.ssknopersonel)
        personelmeslekodası=view.findViewById(R.id.personelmeslekodası)
        rolepersonel=view.findViewById(R.id.rolepersonel)
        ownpersonelcheck=view.findViewById(R.id.ownpersonelcheck)
        tablelayoutpersonelinfo=view.findViewById(R.id.tablelayoutpersonelinfo)
        addFaultsFragment= AddFaultsFragment(requireContext())
        allgetUsers()
        setClickListeners(view)

        changeAdapterDataFault(apiResultUser)

        }
    private fun allgetUsers() {
        UserService.allgetUsers(
            { success ->
                apiResultUser = success as MutableList<User>

                val rvuserliste = mutableListOf<String>()
                for (item in apiResultUser) {
                    rvuserliste.add(item.name)
                    }
                changeAdapterDataUser(apiResultUser)
                adapterUser.onItemClick = {
                    tablelayoutpersonelinfo.isVisible=true
                    sitenameassign.text=Constants.siteAdi
                    blocknameassign.text=Constants.blokAdi
                    regionassign.text=Constants.bolgeAdi
                    locationassign.text=Constants.lokasyonAdi
                    personelname.text=it.name
                    personeljob.text=it.occupation
                    personelphonennumber.text=it.phoneNumber
                    personeltcno.text=it.tcKimlikNo
                    ssknopersonel.text=it.sskNo
                    personelmeslekodası.text=it.professionalChamber
                    rolepersonel.text=it.userRole
                    if(it.ownPersonal){
                        ownpersonelcheck.text="Evet"
                    }            }},
            { failure ->
                print(failure)
            }
        )
    }
    /*
    private fun getFault() {
        ProjectService.getAllFaults(locationID,
            { success ->
                print("oley")
                apiResultFault = success as MutableList<Fault>
                val rvfaultlist = mutableListOf<String>()
                for (item in apiResultFault) {
                    rvfaultlist.add(item.employeeNote)
                }
                changeAdapterDataFault(apiResultFault)
            }
        ) { failure ->
            print(failure)
        }
    }

     */
    private fun changeAdapterDataUser(rvItemList: MutableList<User>) {
        adapterUser = AllUsersAdapter(rvItemList,requireContext())
        rvAllUsers.layoutManager = LinearLayoutManager(requireContext())
       rvAllUsers.adapter = adapterUser
    }


    fun changeAdapterDataFault(rvItemList: MutableList<User>) {
        adapterUser = AllUsersAdapter(rvItemList,requireContext())
        rvAllUsers.layoutManager = LinearLayoutManager(requireContext())
        rvAllUsers.adapter = adapterUser
    }
    private  fun setClickListeners(view: View){
        acceptbuttonforfaults.setOnClickListener {
            replaceFragment(addFaultsFragment)
        }
    }
    private fun replaceFragment(fragment: AddFaultsFragment) {
        val fragmentManager = parentFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.framelayout, fragment)
        fragmentManager.commit()
    }

}







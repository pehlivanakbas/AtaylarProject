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
import com.example.ataylarproject.Adapters.BlockListInterface
import com.example.ataylarproject.Adapters.BlocksAdapter
import com.example.ataylarproject.Models.Block
import com.example.ataylarproject.Models.Site
import com.example.ataylarproject.Network.Services.ProjectService
import com.example.ataylarproject.R
import java.text.FieldPosition


class ProjectBlocksFragment(context: Context) : Fragment(),BlockListInterface {

    lateinit var adapter: BlocksAdapter
    lateinit var rvblockslist: RecyclerView
    lateinit var buttonBack: Button
    lateinit var buttonnext:Button
    lateinit var projectregion:ProjectRegionFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_project_blocks, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonnext=view.findViewById(R.id.buttonnext)
        buttonBack = view.findViewById(R.id.buttonback)
        rvblockslist = view.findViewById(R.id.rvBlocks)
        getBlockList()

buttonnext.setOnClickListener {
    projectregion=ProjectRegionFragment(it.context)
    replaceFragment(projectregion)
}
    }


    private fun createNewBlocks() {
        ProjectService.createBlocks(
            Site("P Blok", "12"),
            { success ->
                print("oley")
                getBlockList()
            },
        ) { failure ->
            print(failure)
        }
    }

    fun getBlockList() {
        ProjectService.getAllBlocks(
            { success ->
                print("oley")
                val result = success as List<Block>

                val rvItemListBlock = mutableListOf<String>()

                for (item in result) {
                    rvItemListBlock.add(item.name)
                }

                changeAdapterData(rvItemListBlock)
                print(result)

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




    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.framelayout, fragment)
        fragmentManager.commit()
    }

    override fun transactionbuttonRegion(position: Int) {
        val regionfragment:Fragment=ProjectRegionFragment(requireContext())
        val fragmentManager:FragmentManager= this.requireActivity().supportFragmentManager
        val fragmentTransaction:FragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,regionfragment)
        fragmentTransaction.commit()
    }


}



package com.example.ataylarproject.Fragments

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Adapters.EmployeeListAdapter
import com.example.ataylarproject.Adapters.EmployeeListInterface
import com.example.ataylarproject.Data.DataEmployee
import com.example.ataylarproject.R
import com.google.android.material.snackbar.Snackbar


class EmployeelistFragment  (context: Context) : Fragment(), EmployeeListInterface {


     private lateinit var adapter: EmployeeListAdapter
    lateinit var rvEmployeelist: RecyclerView
    private lateinit var rvArrayList:ArrayList<DataEmployee>
    lateinit var imageId:Array<Int>
    lateinit var companyName:Array<String>
    lateinit var employeeName:Array<String>
    lateinit var employeeNamem:Array<String>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_employeelist, container, false)


    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            dataInıtıalise()
       /* val data = ArrayList<DataEmployee>()
        for (i in 1..4) {
            data.add(DataEmployee( "ATAYLAR ","Pehlivan AKBAŞ"))*/
            rvEmployeelist = view.findViewById(R.id.rv_employee)
            val addemployeebutton: View =view.findViewById(R.id.fab)
            addemployeebutton.setOnClickListener {
                    view ->
                Snackbar.make(view, "Kayıt ekleme işlemi için yeni bir sayfa açılacaktır", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }
            rvEmployeelist.layoutManager = LinearLayoutManager(context)
            rvEmployeelist.setHasFixedSize(true)
            adapter= EmployeeListAdapter(rvArrayList,this)
            // Setting the Adapter with the recyclerview
            rvEmployeelist.adapter = adapter



    }

   override fun transactionDetailBTPressed(index: Int) {

        Toast.makeText(activity, "Henüz kayıt bulunmamaktadır.", Toast.LENGTH_LONG).show()        }


private fun dataInıtıalise(){
rvArrayList= arrayListOf<DataEmployee>()
    imageId= arrayOf(
        R.drawable.ataylaricon,
        R.drawable.brand,
        R.drawable.suryapi
    )

        companyName= arrayOf(
            getString(R.string.head_1),
            getString(R.string.head_2),
            getString(R.string.head_3),
        )
    employeeName= arrayOf(
        getString(R.string.news_a),
        getString(R.string.news_b),
        getString(R.string.news_c),
    )
    employeeNamem= arrayOf(
        getString(R.string.news_a),
        getString(R.string.news_b),
        getString(R.string.news_c),
    )

    for (i in imageId.indices){
        val employeeNamem=DataEmployee(imageId[i],companyName[i], employeeName[i])
        rvArrayList.add(employeeNamem)
    }




}



}





package com.example.ataylarproject.Fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Adapters.LegislationAdapter
import com.example.ataylarproject.Adapters.LegislationListInterface
import com.example.ataylarproject.Models.LegislationModel
import com.example.ataylarproject.Network.Services.LegislationService
import com.example.ataylarproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FragmentMainLegistlation constructor(context: Context) : Fragment(),
    LegislationListInterface {

    var currentStufe = 1
    var upperLevelId: Int? = null

    lateinit var adapter: LegislationAdapter
    lateinit var rvlegislationlist: RecyclerView
    lateinit var headerText: TextView
    lateinit var cardView: CardView
    var apiResult: MutableList<LegislationModel> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_legistlation, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvlegislationlist = view.findViewById(R.id.rv_legislation)
        headerText = view.findViewById(R.id.headerText)

        val addLegislation: FloatingActionButton = view.findViewById(R.id.fabaddlegislation)

        getLegislations()
        headerText.text = "$currentStufe. Kademe Mevzuatlar"
        addLegislation.setOnClickListener {
            showdialog()
        }

    }

    private fun showdialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Mevzuat Ekle")
        val input = EditText(requireContext())
        input.setHint("Lütfen Mevzuat maddesi giriniz.")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("Kaydet", DialogInterface.OnClickListener { dialog, which ->
            savelegislations(input.text.toString())
        })
        builder.setNegativeButton("İptal",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()


    }

    private fun savelegislations(legislationText: String) {
        LegislationService.createLegislation(LegislationModel(
            id = 0,
            currentStufe,
            legislationText,
            upperLevelId
        ), { success ->
            getLegislations()
        }, { failure ->
            print(failure)
        })
    }

    private fun getLegislations() {
        LegislationService.getLegislations(currentStufe, upperLevelId ?: 0, { success ->
            apiResult = success as MutableList<LegislationModel>
            changeAdapterData(apiResult)
        }, { failure ->
            print(failure)
        })
    }

    private fun changeAdapterData(rvItemList: MutableList<LegislationModel>) {
        adapter = LegislationAdapter(rvItemList, requireContext(), this)
        rvlegislationlist.layoutManager = LinearLayoutManager(requireContext())
        rvlegislationlist.adapter = adapter
    }

    override fun transactionDetailBTPressed(index: Int) {

        print(index)

        upperLevelId = apiResult[index].id
        currentStufe += 1

        updateHeaderText()

        getLegislations()
    }

    fun updateHeaderText() {
        headerText.text = "$currentStufe. Kademe Mevzuatlar"
    }

}
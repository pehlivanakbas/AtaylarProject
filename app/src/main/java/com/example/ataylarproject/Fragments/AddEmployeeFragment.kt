package com.example.ataylarproject.Fragments

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.Company
import com.example.ataylarproject.Models.User
import com.example.ataylarproject.Network.Services.CompanyService
import com.example.ataylarproject.Network.Services.UserService
import com.example.ataylarproject.R


class AddEmployeeFragment : Fragment() {
    private lateinit var EmployeelistFragment: EmployeelistFragment

    private lateinit var name: EditText
    private lateinit var phoneNumberEditText: EditText
    private lateinit var job: EditText
    private lateinit var professionalChamber: EditText
    private lateinit var tckimlik: EditText
    private lateinit var tckimlikserino: EditText
    private lateinit var sskNo: EditText
    private lateinit var registerno: EditText
    private lateinit var employeeInfoCompletedButton: Button
    private lateinit var switchisuserown: Switch
    private var employeemodel: User = User()
    var apiResult: MutableList<User> = mutableListOf()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view=inflater.inflate(R.layout.fragment_add_employee, container, false)
        findViewComponents(view)
        setTextChangeListeners()

    return view
    }

    private fun findViewComponents(view: View) {


        name = view.findViewById(R.id.name)
        phoneNumberEditText = view.findViewById(R.id.phoneNumberEditText)
        //  editTextPassword = view.findViewById(R.id.editTextPassword)
        job = view.findViewById(R.id.job)
        professionalChamber = view.findViewById(R.id.professionalChamber)
        tckimlik = view.findViewById(R.id.tckimlik)
        tckimlikserino = view.findViewById(R.id.tckimlikserino)
        sskNo = view.findViewById(R.id.sskNo)
        registerno = view.findViewById(R.id.registerno)
        switchisuserown=view.findViewById(R.id.switchuserown)

        switchisuserown.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked
                User(ownPersonal = true)

                // Change the app background color
            } else {
                // The switch is disabled
                User(ownPersonal = false)

            }
        }


        employeeInfoCompletedButton = view.findViewById(R.id.employeeInfoCompletedButton)
        employeeInfoCompletedButton.setOnClickListener {
                createEmployeeInfo()
            EmployeelistFragment= EmployeelistFragment(requireContext())
            replaceFragment(EmployeelistFragment)        }

    }

    private fun createEmployeeInfo() {


        val requestBodyemployee = User(0, Constants.ADMIN_ID, employeemodel.name,
            employeemodel.phoneNumber, employeemodel.occupation, employeemodel.professionalChamber,
            employeemodel.registerNo, employeemodel.tcKimlikNo, employeemodel.tcKimlikSeriNo,employeemodel!!.sskNo,
            switchisuserown.isChecked,"EMPLOYEE")


        UserService.createUser(
            requestBodyemployee,
            { success ->
                print(success)
                getUserInfo()

            },
            { failure ->
                print(failure)
            })
    }




    private fun getUserInfo() {
        UserService.getUsers(
            { success ->
               print(success)
                print(apiResult)
            },
            { failure ->
                print(failure)
            }
        )
    }


    private fun setTextChangeListeners() {


        name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                employeemodel?.name = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        phoneNumberEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                employeemodel?.phoneNumber = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        job.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                employeemodel.occupation = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        professionalChamber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                employeemodel?.professionalChamber = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        tckimlik.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                employeemodel?.tcKimlikNo = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        tckimlikserino.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                employeemodel?.tcKimlikSeriNo = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        sskNo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                employeemodel?.sskNo = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        registerno.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                employeemodel?.registerNo = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }

    private fun replaceFragment(employeeFragment: EmployeelistFragment) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.framelayout, EmployeelistFragment(requireContext()))
        fragmentManager?.commit()
    }
    }




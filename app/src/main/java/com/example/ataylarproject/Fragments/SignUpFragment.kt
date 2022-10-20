package com.example.ataylarproject.Fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavDestination
import com.example.ataylarproject.MainActivity
import com.example.ataylarproject.ProjectsFragment
import com.example.ataylarproject.R
import com.example.ataylarproject.databinding.ActivityMainBinding


@Suppress("UNREACHABLE_CODE")
class SignUpFragment (private val mainActivity: MainActivity) : Fragment() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userNameEditText: EditText
    private lateinit var CompanyName: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var emailEditText: EditText
    private lateinit var CompanySocialMediaName: EditText
    private lateinit var signUpFormCompletedButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_sign_up, container, false)

      //  findViewComponents(view)




       fun findViewComponents(view: View) {
            userNameEditText = view.findViewById(R.id.userNameEditText)
            CompanyName = view.findViewById(R.id.CompanyName)
            phoneNumber = view.findViewById(R.id.phoneNumberEditText)
            editTextPassword = view.findViewById(R.id.editTextPassword)
            emailEditText = view.findViewById(R.id.emailEditText)
            CompanySocialMediaName = view.findViewById(R.id.CompanySocialMediaName)
           signUpFormCompletedButton=view.findViewById(R.id.signUpFormCompletedButton)
        }

        signUpFormCompletedButton.setOnClickListener {
            replaceFragment(NavDestination.LOGIN)

        }
        /*  private fun setTextChangeListeners() {


            userNameEditText.addTextChangedListener(object : TextWatcher {
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
                 //   smsValidationCode = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

            userNameEditText.addTextChangedListener(object : TextWatcher {
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
                //    userNameEditText= s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

            emailEditText.addTextChangedListener(object : TextWatcher {
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
                    email = s.toString()
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
                    phoneNumber = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

        }
    }
*/
    }
    private fun replaceFragment(navDestination: NavDestination) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        when (navDestination) {
            NavDestination.SIGNUP -> fragmentManager?.replace(R.id.framelayout,SignUpFragment(MainActivity()) )
            NavDestination.LOGIN -> fragmentManager?.replace(R.id.framelayout, ProjectsFragment(requireContext()))
        }
        fragmentManager?.commit()
    }

enum class NavDestination{
    LOGIN,SIGNUP
}}
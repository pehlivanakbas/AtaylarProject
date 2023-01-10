package com.example.ataylarproject.Activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.at.CompanyInfoFragment
import com.example.ataylarproject.Fragments.*
import com.example.ataylarproject.Models.LegislationModel
import com.example.ataylarproject.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    // Initialise the DrawerLayout, NavigationView and ToggleBar
    private lateinit var drawerLayout: DrawerLayout
    lateinit var appbarConfigration:AppBarConfiguration
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private val projectSitesFragment = ProjectSitesFragment(this)
    private val EmployeelistFragment = EmployeelistFragment(this)
    private val CompanyInfoFragment = CompanyInfoFragment(this)
    private val LegislationFragment = FragmentMainLegistlation(this)
    private val documentsfragment = DocumentFragment(this)





    // override the onSupportNavigateUp() function to launch the Drawer when the hamburger icon is clicked
    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Call findViewById on the DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Pass the ActionBarToggle action into the drawerListener
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()


        // Call findViewById on the NavigationView
        navView = findViewById(R.id.navView)


        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.company_info -> {
                    replaceFragment(EmployeelistFragment)
                    true

                }
                R.id.projects -> {
                   replaceFragment(projectSitesFragment)
                    true
                }
                R.id.personel_list -> {
                   replaceFragment(CompanyInfoFragment)
                    true
                }
                R.id.addphotoformsidebar -> {
                    val intent = Intent(this, PhotoActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.Faults -> {
                    replaceFragment(AssignProjectFragment(this))
                    //startActivity(intent)
                    true
                }

                R.id.addFaults -> {
                    replaceFragment(AddFaultsFragment(this))
                    //startActivity(intent)
                    true
                }
                R.id.AddUser -> {
                    replaceFragment(AddEmployeeFragment())
                    //startActivity(intent)
                    true
                }
                R.id.exitbutton -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.legislation -> {
                    replaceFragment(FragmentMainLegistlation(this))
                    //startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }}

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.framelayout, fragment)
        fragmentManager.commit() //fragmentler commit metod ile begin metodu arasında olmalı commit olmadan kod calışmayacaktır.
    }


}





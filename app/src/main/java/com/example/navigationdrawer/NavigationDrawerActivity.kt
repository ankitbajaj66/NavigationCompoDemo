package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigationcompodemo.R
import kotlinx.android.synthetic.main.activity_navigation_drawer.*

class NavigationDrawerActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)

        setSupportActionBar(toolbar)
        navController = findNavController(R.id.nav_host_fragment)


        // This will show back arrow and clicking will be navigate to first item
        appBarConfiguration = AppBarConfiguration(
            navController.graph,
            drawer_layout
        )

//        // This will not show back arrow
//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.nav_home, R.id.nav_Slideshow, R.id.nav_send, R.id.nav_share),
//            drawer_layout
//        )

        // This will take care showing 3 bars and arrow when drawer is open
        setupActionBarWithNavController(navController, appBarConfiguration)

        nav_view.setupWithNavController(navController)
        bottom_nav.setupWithNavController(navController)

    }

    // This method is for actual navigation
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

package com.manohar.vehiclerecords.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.manohar.vehiclerecords.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController

    }



}
package com.parita.downloadapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        intent?.let {
            if(it.getStringExtra("message")!=null && it.getStringExtra("message").equals("DetailsFragment",true)){
                navController.navigate(R.id.detailsFragment)
            }
        }
    }
}
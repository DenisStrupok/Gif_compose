package com.testinggifsproject.features.main

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testinggifsproject.features.home.HomeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = Color.BLACK
            window.navigationBarColor = Color.BLACK
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "HomeScreen") {
                composable("HomeScreen") {
                    HomeScreen()
                }
            }
        }
    }
}

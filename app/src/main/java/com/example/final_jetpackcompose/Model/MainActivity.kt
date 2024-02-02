@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.final_jetpackcompose.Model

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.final_jetpackcompose.view.WorldClockApp
import com.example.final_jetpackcompose.view.cityInfoScreen
import com.example.final_jetpackcompose.view.citySelec


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Pantalla01.route
                    ) {
                        composable("pantalla02") { cityInfoScreen(navigationController, citySelec) }
                        composable(Routes.Pantalla01.route) { WorldClockApp(navigationController) }
                        composable(Routes.Pantalla02.route) { cityInfoScreen(navigationController, citySelec) }
                    }

                }
        }
    }
}



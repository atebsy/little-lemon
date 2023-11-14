package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Navigation(sharedPreferences: SharedPreferences,
               navController: NavHostController,
               menuItems: List<MenuItem>,
               context: Context){

    val userEmail = sharedPreferences.getString("email","")
    var startDestinations = Onboarding.route

    if (userEmail!!.isNotBlank()){
        startDestinations = Home.route
    }

    NavHost(navController = navController,
        startDestination = startDestinations){
        composable(Onboarding.route){
            OnboardingScreen(navController,sharedPreferences,context)
        }

        composable(Home.route){
            HomeScreen(menuItems, navController)
        }

        composable(Profile.route){
            ProfileScreen(navController,sharedPreferences)
        }
    }
}
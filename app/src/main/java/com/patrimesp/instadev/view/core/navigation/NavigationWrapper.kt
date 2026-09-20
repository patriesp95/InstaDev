package com.patrimesp.instadev.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aristidevs.instadev.view.core.navigation.Login
import com.patrimesp.instadev.view.auth.register.RegisterScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            RegisterScreen()
        }
    }
}
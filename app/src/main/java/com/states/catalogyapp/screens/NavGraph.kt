package com.states.catalogyapp.screens

import ProductDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavGraph() {
    val navController = rememberNavController() // this is our navigation controller

    NavHost(
        navController = navController,
        startDestination = "product_list" // initial screen
    ) {
        composable("product_list") { // product list screen
            ProductListScreen { productId ->
                navController.navigate("product_detail/$productId") // Navigate to details
            }
        }
        composable(
            route = "product_detail/{productId}", // Dynamic route
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            ProductDetailScreen(productId = productId)
        }
    }
}
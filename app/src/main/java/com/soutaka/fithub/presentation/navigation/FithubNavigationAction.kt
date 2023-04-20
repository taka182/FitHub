package com.soutaka.fithub.presentation.navigation

import androidx.navigation.NavController

object FithubDestinations {
    const val BODY_METRICS_ROUTE = "body_metrics"
    const val PROFILE_ROUTE = "profile"
}

class FithubNavigationAction(private val navController: NavController) {

    fun navigateToBodyMetrics() {
        navController.navigate(FithubDestinations.BODY_METRICS_ROUTE) {
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }

                launchSingleTop = true
                restoreState = true
            }
        }
    }

    fun navigateToProfile() {
        navController.navigate(FithubDestinations.PROFILE_ROUTE) {
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }

                launchSingleTop = true
                restoreState = true
            }
        }
    }
}

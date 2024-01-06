package com.shahinbashar.qsandroid

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shahinbashar.qsandroid.features.history.presentation.pages.HistoryScreen
import com.shahinbashar.qsandroid.features.login.view.login.LoginNewScreen
import com.shahinbashar.qsandroid.features.home.view.DashboardScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    val startDestination = if (prefs.loginModel.isLoggedIn) nav_routes.HOME else nav_routes.LOGIN

    NavHost(navController = navController, startDestination = startDestination) {

        composable(nav_routes.LOGIN) {
            LoginNewScreen(navController = navController)

        }
        composable(nav_routes.HOME) {
            DashboardScreen(navController)
        }
        composable(nav_routes.HISTORY) {
            HistoryScreen(navController)
        }

    }
}

//private inline fun <reified T> NavBackStackEntry.getModel(): T? {
//    return Uri.decode(arguments?.getString(nav_routes.MODEL))?.fromJson<T>()
//}


object nav_routes {
    const val HOME = "home"
    const val LOGIN = "login"
    const val HISTORY= "history"
}

package the_null_pointer.secure_device

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import the_null_pointer.secure_device.ui.NavItem
import the_null_pointer.secure_device.ui.loading.Loading
import the_null_pointer.secure_device.ui.result.Result
import the_null_pointer.secure_device.ui.search.Search
import the_null_pointer.secure_device.ui.splash.Splash
import the_null_pointer.secure_device.ui.theme.SecureDeviceTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecureDeviceTheme {
                val navController = rememberNavController()
                NavigationGraph(navController = navController)
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Splash.screenRoute,
    ) {
        composable(
            route = NavItem.Splash.screenRoute,
        ) {
            Splash(
                onDatabaseReady = {
                    navController.navigate(NavItem.Search.screenRoute) {
                        popUpTo(NavItem.Splash.screenRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = NavItem.Search.screenRoute,
        ) {
            Search(onDeviceClick = { navController.navigate(NavItem.Loading.screenRoute + "/" + it.id) })
        }
        composable(
            route = NavItem.Loading.screenRoute + "/{id}",
        ) {
            Loading(onResultReady = { deviceId ->
                navController.navigate(NavItem.Result.screenRoute + "/" + deviceId) {
                    popUpTo(NavItem.Search.screenRoute)
                }
            })
        }
        composable(
            route = NavItem.Result.screenRoute + "/{id}",
        ) {
            Result(onBackClicked = {
                navController.navigate(NavItem.Search.screenRoute) {
                    popUpTo(NavItem.Search.screenRoute) {
                        inclusive = true
                    }
                }
            })
        }
    }
}

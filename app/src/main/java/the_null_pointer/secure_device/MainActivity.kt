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
import the_null_pointer.secure_device.ui.loading.LoadingScreen
import the_null_pointer.secure_device.ui.result.Result
import the_null_pointer.secure_device.ui.search.Search
import the_null_pointer.secure_device.ui.splash.Splash
import the_null_pointer.secure_device.ui.splash.SplashScreen
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
            Splash(onDatabaseReady =
            {navController.navigate(NavItem.SearchScreen.screenRoute)}
            )
        }
        composable(
            route = NavItem.SearchScreen.screenRoute,
        ) {
            Search()
        }
        composable(
            route = NavItem.Loading.screenRoute,
        ) {
            Loading(onResultReady = {navController.navigate(NavItem.Result.screenRoute)})
        }
        composable(
            route = NavItem.Result.screenRoute,
        ) {
            Result(onBackClicked = {navController.navigate(NavItem.SearchScreen.screenRoute){
                popUpTo(NavItem.SearchScreen.screenRoute) {
                    inclusive = true
                }
            } })
        }
    }
}

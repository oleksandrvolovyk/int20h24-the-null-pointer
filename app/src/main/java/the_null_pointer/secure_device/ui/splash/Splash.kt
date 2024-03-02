package the_null_pointer.secure_device.ui.splash


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Splash(
    //viewModel: SplashScreenViewModel = hiltViewModel(),
    onDatabaseReady: () -> Unit) {


    SplashScreen(onDatabaseReady = onDatabaseReady)

}


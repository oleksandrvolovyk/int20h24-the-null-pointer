package the_null_pointer.secure_device.ui.loading

import androidx.compose.runtime.Composable

@Composable
fun Loading(
    //viewModel: SplashScreenViewModel = hiltViewModel(),
    onResultReady: () -> Unit) {


    LoadingScreen(onResultReady = onResultReady)

}

package the_null_pointer.secure_device.ui.result

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Result( //viewModel: SplashScreenViewModel = hiltViewModel(),
    onBackClicked: () -> Unit) {

    ResultScreen(onBackClicked = onBackClicked)

}
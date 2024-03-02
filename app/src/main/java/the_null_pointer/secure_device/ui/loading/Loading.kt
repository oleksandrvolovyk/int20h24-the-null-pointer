package the_null_pointer.secure_device.ui.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Loading(
    viewModel: LoadingViewModel = hiltViewModel(),
    onResultReady: (String) -> Unit) {

    val deviceId = viewModel.getdeviceId()
    LoadingScreen(deviceId = deviceId, onResultReady = onResultReady)

}

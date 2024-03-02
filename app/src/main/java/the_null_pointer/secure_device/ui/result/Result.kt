package the_null_pointer.secure_device.ui.result

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Result( viewModel: ResultViewModel = hiltViewModel(),
    onBackClicked: () -> Unit) {

    val uiState by viewModel.uiState.collectAsState()

    ResultScreen(uiState = uiState, onBackClicked = onBackClicked)

}
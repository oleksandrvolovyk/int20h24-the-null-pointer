package the_null_pointer.secure_device.ui.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import the_null_pointer.secure_device.data.model.Device

@Composable
fun Search(
    viewModel: SearchViewModel = hiltViewModel(),
    onDeviceClick: (Device) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadDevices()
    }

    SearchScreen(
        uiState = uiState,
        onQueryChange = { query -> viewModel.getSearchHints(query)},
        onSearch = { viewModel.search(it) },
        onSelectedDeviceTypeChange = { viewModel.selectDeviceType(it) },
        onSelectedDeviceBrandChange = { viewModel.selectDeviceBrand(it) },
        onSelectedDeviceModelChange = { viewModel.selectDeviceModel(it) },
        onDeviceClick = onDeviceClick
    )
}
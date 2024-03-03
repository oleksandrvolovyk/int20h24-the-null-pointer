package the_null_pointer.secure_device.ui.search

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import the_null_pointer.secure_device.R
import the_null_pointer.secure_device.data.model.Device
import the_null_pointer.secure_device.ui.search.widgets.ExpandableDeviceCard
import the_null_pointer.secure_device.ui.search.widgets.SearchBar
import the_null_pointer.secure_device.ui.search.widgets.Spinner
import the_null_pointer.secure_device.ui.uitl.StringUtil.capitalize

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onSelectedDeviceTypeChange: (String?) -> Unit,
    onSelectedDeviceBrandChange: (String?) -> Unit,
    onSelectedDeviceModelChange: (String?) -> Unit,
    onDeviceClick: (Device) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            searchHints = uiState.searchHints,
            onQueryChange = onQueryChange,
            onSearch = onSearch
        )

        Spacer(Modifier.height(4.dp))

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(R.string.filters),
            fontSize = 20.sp
        )

        Spacer(Modifier.height(4.dp))

        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
            horizontalArrangement = spacedBy(4.dp)
        ) {
            Spinner(
                modifier = Modifier.weight(1f),
                items = uiState.deviceTypes.map { it to it.capitalize() },
                selected = uiState.selectedDeviceType,
                onSelectionChanged = onSelectedDeviceTypeChange,
                canSelectNothing = true,
                nothingOptionString = stringResource(R.string.device_type),
                maxLines = 1
            )

            Spinner(
                modifier = Modifier.weight(1f),
                items = uiState.deviceBrands.map { it to it },
                selected = uiState.selectedDeviceBrand,
                onSelectionChanged = onSelectedDeviceBrandChange,
                canSelectNothing = true,
                nothingOptionString = stringResource(R.string.device_brand),
                maxLines = 1
            )

            Spinner(
                modifier = Modifier.weight(1f),
                items = uiState.deviceModels.map { it to it },
                selected = uiState.selectedDeviceModel,
                onSelectionChanged = onSelectedDeviceModelChange,
                canSelectNothing = true,
                nothingOptionString = stringResource(R.string.device_model),
                maxLines = 1
            )
        }

        Spacer(Modifier.height(8.dp))

        Divider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)

        Spacer(Modifier.height(4.dp))

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(R.string.devices),
            fontSize = 20.sp
        )

        Spacer(Modifier.height(4.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 4.dp),
            verticalArrangement = spacedBy(4.dp)
        ) {
            items(uiState.devices, key = { it.id }) {
                ExpandableDeviceCard(
                    modifier = Modifier.fillMaxWidth(),
                    device = it,
                    onAnalyzeButtonClick = onDeviceClick
                )
            }
        }
    }
}
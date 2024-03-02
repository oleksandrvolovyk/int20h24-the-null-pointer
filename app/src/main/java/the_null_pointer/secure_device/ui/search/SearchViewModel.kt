package the_null_pointer.secure_device.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import the_null_pointer.secure_device.data.DeviceRepository
import the_null_pointer.secure_device.data.model.Device
import javax.inject.Inject

data class SearchUiState(
    // Pair<Brand + model, only model>
    val searchHints: List<Pair<String, String>> = emptyList(),

    val selectedDeviceType: String? = null,
    val deviceTypes: List<String> = emptyList(),
    val selectedDeviceBrand: String? = null,
    val deviceBrands: List<String> = emptyList(),
    val selectedDeviceModel: String? = null,
    val deviceModels: List<String> = emptyList(),

    val devices: List<Device> = emptyList()
)

private const val DEBOUNCE_PERIOD = 500L // 0.5 seconds

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun loadDevices() = viewModelScope.launch {
        val devices = deviceRepository.getAll()
        _uiState.update {
            it.copy(
                deviceTypes = devices.map { device -> device.type }.distinct(),
                deviceBrands = devices.map { device -> device.brand }.distinct(),
                deviceModels = devices.map { device -> device.model }.distinct(),
                devices = devices
            )
        }
    }

    private var searchHintsJob: Job? = null

    fun getSearchHints(query: String) = viewModelScope.launch {
        searchHintsJob?.cancel() // Cancel the previous job if it exists

        searchHintsJob = viewModelScope.launch {
            delay(DEBOUNCE_PERIOD) // Delay the execution of the request

            val devices = deviceRepository.search(query)

            _uiState.update {
                it.copy(
                    searchHints = devices
                        .map { device -> "${device.brand} ${device.model}" to device.model }
                )
            }
        }
    }

    fun search(query: String) = viewModelScope.launch {
        val allDevices = deviceRepository.getAll()
        // Remove filters
        _uiState.update {
            it.copy(
                selectedDeviceType = null,
                deviceTypes = allDevices.map { device -> device.type }.distinct(),
                selectedDeviceBrand = null,
                deviceBrands = allDevices.map { device -> device.brand }.distinct(),
                selectedDeviceModel = null,
                deviceModels = allDevices.map { device -> device.model }.distinct(),
            )
        }
        _uiState.update { it.copy(devices = deviceRepository.search(query)) }
    }

    fun selectDeviceType(deviceType: String?) = viewModelScope.launch {
        val allDevices = deviceRepository.getAll()

        val filteredDevices = allDevices.filterDevices(
            deviceType,
            brand = null,
            model = null
        )

        _uiState.update {
            it.copy(
                selectedDeviceType = deviceType,
                selectedDeviceBrand = null,
                deviceBrands = filteredDevices.map { device -> device.brand }.distinct(),
                selectedDeviceModel = null,
                deviceModels = filteredDevices.map { device -> device.model }.distinct(),
                devices = filteredDevices
            )
        }
    }

    fun selectDeviceBrand(deviceBrand: String?) = viewModelScope.launch {
        val allDevices = deviceRepository.getAll()

        val filteredDevices = allDevices.filterDevices(
            uiState.value.selectedDeviceType,
            deviceBrand,
            null
        )

        _uiState.update {
            it.copy(
                selectedDeviceBrand = deviceBrand,
                selectedDeviceModel = null,
                deviceModels = filteredDevices.map { device -> device.model }.distinct(),
                devices = filteredDevices
            )
        }
    }

    fun selectDeviceModel(deviceModel: String?) = viewModelScope.launch {
        val allDevices = deviceRepository.getAll()
        _uiState.update {
            it.copy(
                selectedDeviceModel = deviceModel,
                devices = allDevices.filterDevices(
                    uiState.value.selectedDeviceType,
                    uiState.value.selectedDeviceBrand,
                    deviceModel
                )
            )
        }
    }

    private fun List<Device>.filterDevices(type: String?, brand: String?, model: String?) =
        filter { device ->
            (type == null || device.type == type) &&
                    (brand == null || device.brand == brand) &&
                    (model == null || device.model == model)
        }

    fun analyzeDevice(device: Device) {
        // Navigate to LoadingScreen, and then to ResultScreen
        TODO("Not yet implemented")
    }
}

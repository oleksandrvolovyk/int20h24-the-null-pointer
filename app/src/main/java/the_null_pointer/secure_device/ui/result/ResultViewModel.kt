package the_null_pointer.secure_device.ui.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fasterxml.jackson.annotation.JsonProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import the_null_pointer.secure_device.data.DeviceRepository
import the_null_pointer.secure_device.data.model.Device
import the_null_pointer.secure_device.ui.search.SearchUiState
import javax.inject.Inject

data class ResultUiState(
    val device: Device? = null
)

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    private val deviceId = savedStateHandle.get<String>("id")!!.toLong()

    private val _uiState = MutableStateFlow(ResultUiState())
    val uiState: StateFlow<ResultUiState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            val device = deviceRepository.getById(deviceId)
            _uiState.update {
                it.copy(
                    device = device
                )
            }
        }

    }





}

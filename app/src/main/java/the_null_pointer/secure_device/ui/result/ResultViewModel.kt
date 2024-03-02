package the_null_pointer.secure_device.ui.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.annotation.JsonProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import the_null_pointer.secure_device.data.DeviceRepository
import the_null_pointer.secure_device.data.model.Device
import javax.inject.Inject

data class ResultUiState(
    val id: Long,
    val type: String,
    val brand: String,
    val model: String,
    val hasVideo: Boolean,
    val hasWifi: Boolean,
    val has24GhzWifi: Boolean,
    val has5GhzWifi: Boolean,
    val securityProtocol: String?,
    val hasPrivacyShutter: Boolean?,
    val encryption: String?,
    val isSecure: Boolean?,
    val infoLink: String?,
    val comments: String?

)

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    // collect if i can move to SearchScreen

    private val deviceId = savedStateHandle.get<String>("id")!!




}

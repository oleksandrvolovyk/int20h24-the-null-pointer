package the_null_pointer.secure_device.ui.loading


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import the_null_pointer.secure_device.data.DeviceRepository
import javax.inject.Inject

@HiltViewModel
class LoadingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val deviceRepository: DeviceRepository
) : ViewModel() {


    private val deviceId = savedStateHandle.get<String>("id")!!

    fun getdeviceId(): String{
        return deviceId
    }


}

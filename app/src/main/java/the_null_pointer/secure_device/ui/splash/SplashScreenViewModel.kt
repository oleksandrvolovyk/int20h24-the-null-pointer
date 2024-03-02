package the_null_pointer.secure_device.ui.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import the_null_pointer.secure_device.data.DeviceRepository
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    // collect if i can move to SearchScreen

}

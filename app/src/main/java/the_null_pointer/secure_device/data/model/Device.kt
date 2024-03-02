package the_null_pointer.secure_device.data.model

data class Device(
    val type: String,
    val brand: String,
    val model: String,
    val hasVideo: Boolean,
    val hasWifi: Boolean,
    val has24GhzWifi: Boolean,
    val has5GhzWifi: Boolean,
    val securityProtocal: String?,
    val hasPrivacyShutter: Boolean?,
    val encryption: String?,
    val isSecure: Boolean?,
    val infoLink: String?,
    val comments: String?
)
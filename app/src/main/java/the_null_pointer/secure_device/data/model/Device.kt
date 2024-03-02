package the_null_pointer.secure_device.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Device(
    val id: Long,
    val type: String,
    val brand: String,
    val model: String,
    @JsonProperty("video") val hasVideo: Boolean,
    @JsonProperty("wifi") val hasWifi: Boolean,
    @JsonProperty("ghz24") val has24GhzWifi: Boolean,
    @JsonProperty("ghz5") val has5GhzWifi: Boolean,
    @JsonProperty("protocol") val securityProtocol: String?,
    @JsonProperty("privacy_shutter") val hasPrivacyShutter: Boolean?,
    @JsonProperty("encryption") val encryption: String?,
    @JsonProperty("is_secure") val isSecure: Boolean?,
    @JsonProperty("info_link") val infoLink: String?,
    @JsonProperty("comments") val comments: String?
)
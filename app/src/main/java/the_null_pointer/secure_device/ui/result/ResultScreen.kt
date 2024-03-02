package the_null_pointer.secure_device.ui.result

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import the_null_pointer.secure_device.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay
import the_null_pointer.secure_device.data.model.Device

@Composable
fun ResultScreen(uiState: ResultUiState, onBackClicked: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.background(color = SecureState(device = uiState.device).first)) {

            Box(modifier = Modifier.fillMaxWidth()) {
                Icon(painter = painterResource(R.drawable.arrow_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onBackClicked() }
                        .align(Alignment.CenterStart))
                Text(
                    text = stringResource(id = R.string.model, uiState.device?.model ?: ""),
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Text(
                text = SecureState(device = uiState.device).second,
                modifier = Modifier
                    .padding(12.dp),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier

                .wrapContentSize()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.device_characteristics),
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(color = Color.Gray.copy(alpha = 0.3f))
                    ,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Start

                )
                DesignedText(
                    nameColumnFromBD = stringResource(id = R.string.device_type),
                    columnText = uiState.device?.type ?: ""
                )
                DesignedText(
                    nameColumnFromBD = stringResource(id = R.string.device_brand),
                    columnText = uiState.device?.brand ?: ""
                )
                DesignedText(
                    nameColumnFromBD = stringResource(id = R.string.device_model),
                    columnText = uiState.device?.model ?: ""
                )
                DesignedText(
                    nameColumnFromBD = stringResource(id = R.string.device_wifi),
                    columnText = WifiState(
                        device = uiState.device
                    )
                )
                DesignedText(
                    nameColumnFromBD = stringResource(id = R.string.device_security_protocol),
                    columnText = uiState.device?.securityProtocol ?: stringResource(
                        id = R.string.not_available
                    )
                )
                DesignedText(
                    nameColumnFromBD = stringResource(id = R.string.privacy_shutter),
                    columnText = PrivacyShutterState(
                        device = uiState.device
                    )
                )
                DesignedText(
                    nameColumnFromBD = stringResource(id = R.string.device_encryption),
                    columnText = uiState.device?.encryption ?: stringResource(
                        id = R.string.not_available
                    )
                )
                DesignedText(
                    nameColumnFromBD = stringResource(id = R.string.comments),
                    columnText = uiState.device?.comments ?: stringResource(
                        id = R.string.no_comments_about_device
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                Divider(color = MaterialTheme.colorScheme.outline, thickness = 2.dp)

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = stringResource(id = R.string.comments),
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 25.sp,
                    textAlign = TextAlign.Start
                )

                Column(
                    modifier = Modifier
                        .verticalScroll(enabled = true, state = rememberScrollState())
                ) {
                    Text(
                        text = uiState.device?.comments
                            ?: stringResource(id = R.string.no_comments_about_device),
                        modifier = Modifier
                            .padding(start = 25.dp, bottom = 10.dp),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        maxLines = 2
                    )
                }
                Divider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)

            }

        }

        if (uiState.device?.infoLink != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 10.dp)
                    .offset(y = (5).dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                val context = LocalContext.current

                Button(
                    shape = RoundedCornerShape(0),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(uiState.device.infoLink)
                        }
                        context.startActivity(intent)
                    }) {
                    Text(
                        stringResource(id = R.string.read_more),
                        fontSize = 20.sp
                    )

                }
            }
        }
    }
}

@Composable
fun WifiState(device: Device?): String {
    if (device != null) {
        if (device.hasWifi && device.has24GhzWifi && device.has5GhzWifi) {
            return stringResource(R.string.wifi24and5)
        } else if (device.hasWifi && device.has24GhzWifi) {
            return stringResource(R.string.wifi24)
        } else if (device.hasWifi && device.has5GhzWifi) {
            return stringResource(R.string.wifi5)
        }
    }
    return stringResource(id = R.string.no_wifi)
}

@Composable
fun PrivacyShutterState(device: Device?): String {
    if (device != null) {
        if (device.hasPrivacyShutter == true) {
            return stringResource(id = R.string.has_privacy_shutter)
        } else if (device.hasPrivacyShutter == false) {
            return stringResource(id = R.string.does_not_have_privacy_shutter)
        }

    }
    return stringResource(id = R.string.not_available)
}

@Composable
fun SecureState(device: Device?): Pair<Color, String> {
    if (device != null) {
        if (device.isSecure == true) {
            return Color.Green.copy(alpha = 0.2f) to stringResource(id = R.string.is_secure)
        } else if (device.isSecure == false) {
            return Color.Red.copy(alpha = 0.2f) to stringResource(id = R.string.not_secure)
        }
    }
    return Color.Yellow.copy(alpha = 0.2f) to stringResource(id = R.string.not_enough_information_about_security)
}


@Composable
fun DesignedText(nameColumnFromBD: String, columnText: String) {
    Row(
        modifier = Modifier
            .padding(start = 25.dp, top = 5.dp, end = 10.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = nameColumnFromBD,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 19.sp,
                color = Color.Gray
            )
            Text(
                text = columnText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontSize = 19.sp
            )
        }
    }
}

@Preview
@Composable
fun ResultPreview() {
    val uiState = ResultUiState(

    )
    ResultScreen(uiState, onBackClicked = {})
}

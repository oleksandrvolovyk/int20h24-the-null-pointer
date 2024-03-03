package the_null_pointer.secure_device.ui.search.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import the_null_pointer.secure_device.R
import the_null_pointer.secure_device.data.model.Device
import the_null_pointer.secure_device.ui.uitl.StringUtil.capitalize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableDeviceCard(
    modifier: Modifier = Modifier,
    device: Device,
    onAnalyzeButtonClick: (Device) -> Unit
) {
    var expandedState by remember { mutableStateOf(false) }

    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = modifier,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${device.type.capitalize()} ${device.brand} ${device.model}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(
                modifier = Modifier
                    .alpha(0.2f)
                    .rotate(rotationState),
                onClick = {
                    expandedState = !expandedState
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop-Down Arrow"
                )
            }
        }
        AnimatedVisibility(visible = expandedState) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    if (device.hasWifi && device.has24GhzWifi && device.has5GhzWifi) {
                        Text(
                            modifier = modifier.weight(1f),
                            text = stringResource(R.string.wifi24and5),
                            textAlign = TextAlign.Center
                        )
                    } else if (device.hasWifi && device.has24GhzWifi) {
                        Text(
                            modifier = modifier.weight(1f),
                            text = stringResource(R.string.wifi24),
                            textAlign = TextAlign.Center
                        )
                    } else if (device.hasWifi && device.has5GhzWifi) {
                        Text(
                            modifier = modifier.weight(1f),
                            text = stringResource(R.string.wifi5),
                            textAlign = TextAlign.Center
                        )
                    } else if (!device.hasWifi) {
                        Text(
                            modifier = modifier.weight(1f),
                            text = stringResource(R.string.no_wifi),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.width(16.dp))

                    if (device.hasVideo) {
                        Text(
                            modifier = modifier.weight(1f),
                            text = stringResource(R.string.has_video),
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(
                            modifier = modifier.weight(1f),
                            text = stringResource(R.string.does_not_have_video),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Button(onClick = { onAnalyzeButtonClick(device) }) {
                    Text(stringResource(R.string.analyze_device))
                }
            }
        }
    }
}
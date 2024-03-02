package the_null_pointer.secure_device.ui.splash

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import the_null_pointer.secure_device.R
import the_null_pointer.secure_device.ui.loading.Loading

@Composable
fun SplashScreen(onDatabaseReady: () -> Unit) {

    // if i can move to the DB call
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scale = infiniteTransition.animateFloat(
        0.5f,
        1f,
        // No offset for the 1st animation
        infiniteRepeatable(tween(2000), RepeatMode.Reverse), label = ""
    )
    // TODO: change this to depend on the flow from view model
    LaunchedEffect(Unit) {
        delay(2000) // Wait for 2000 milliseconds
        onDatabaseReady() // Execute the lambda
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(scale.value)
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_screen_icon),
                contentDescription = "App Icon",
                modifier = Modifier.size(140.dp)
            )
            Text(
                text = "Secure Device",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 26.sp
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun SplashPreview() {
    Box(modifier = Modifier.fillMaxSize())
    SplashScreen(onDatabaseReady = {})
}



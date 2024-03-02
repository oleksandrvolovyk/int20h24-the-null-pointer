package the_null_pointer.secure_device.ui.result

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

@Composable
fun ResultScreen( onBackClicked: () -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.background(color = Color.Red.copy(alpha = 0.2f))){

            Box(modifier = Modifier.fillMaxWidth()){
                Icon(painter = painterResource(R.drawable.arrow_back),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                        .clickable { onBackClicked() }
                        .align(Alignment.CenterStart))
                Text(text = "Model: data from DB",
                    fontSize = 25.sp,
                    modifier = Modifier.align(Alignment.Center))

            }


            Text(
                //TODO: fetch from DB
                text = "Unfortunately, we do not have enough information about the device",
                modifier = Modifier
                    .padding(12.dp)
                ,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }


        Spacer(modifier = Modifier.height(10.dp))
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 12.dp)
//                .clip(RoundedCornerShape(25))
//                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f))
//                .wrapContentSize()
//                ,
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                //TODO: fetch from DB
//                text = "Unfortunately, we do not have enough information about the device",
//                modifier = Modifier
//                    .padding(12.dp)
//                ,
//                fontSize = 25.sp,
//                textAlign = TextAlign.Center
//            )
//        }

        Spacer(modifier = Modifier.size(2.dp))

        Box(
            modifier = Modifier
                //.clip(RoundedCornerShape(10))
                .wrapContentSize()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
            ,
            contentAlignment = Alignment.Center
        ) {


            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
            ){
                Text(
                    //TODO: fetch from DB
                    text = "Device characteristics",
                    modifier = Modifier
                        .padding(start = 16.dp, top = 10.dp)
                    ,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
                DesignedText(nameColumnFromBD = "Type", columnText = "type")
                DesignedText(nameColumnFromBD = "Type", columnText = "Characteristick")
                DesignedText(nameColumnFromBD = "Type", columnText = "Model model")
                DesignedText(nameColumnFromBD = "Type", columnText = "12 billion ")
                DesignedText(nameColumnFromBD = "Type", columnText = "Content")
                DesignedText(nameColumnFromBD = "Type", columnText = "No Content")
                DesignedText(nameColumnFromBD = "Type", columnText = "FLow")
                DesignedText(nameColumnFromBD = "Type", columnText = "It is not secure")
                DesignedText(nameColumnFromBD = "Type", columnText = "type")



                Spacer(modifier = Modifier.height(5.dp))

                Divider(color = MaterialTheme.colorScheme.outline, thickness = 2.dp)

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    //TODO: fetch from DB
                    text = "Coments",
                    modifier = Modifier
                        .padding(start = 16.dp)
                    ,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )

                Column( modifier = Modifier
                    .verticalScroll( enabled = true, state = rememberScrollState())){
                    Text(
                        text = "Your comments here",
                        modifier = Modifier
                            .padding(start = 25.dp, bottom = 10.dp)
                        ,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        maxLines = 2
                    )
                }





            }

        }

        //TODO: fetch secure or not

        var secure = true
        if(secure){
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(top = 10.dp),
                contentAlignment = Alignment.BottomCenter){
                Button( shape = RoundedCornerShape(0), modifier = Modifier.fillMaxWidth(),  onClick = { /*TODO*/ }) {
                    Text("Read more link",
                        fontSize = 20.sp)

                }


            }

        }
    }
}
@Composable
fun DesignedText(nameColumnFromBD: String, columnText:String){
    Row(modifier = Modifier
        .padding(start = 25.dp, top = 5.dp, end = 10.dp)) {
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
    ResultScreen(onBackClicked = {})
}

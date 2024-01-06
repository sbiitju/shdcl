package com.shdclgroup.app.core.base.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shdclgroup.app.R
import com.shdclgroup.app.core.base.component.SmallSpacer
import com.shdclgroup.app.core.theme.AppTheme


@Composable
fun EmptyView(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {

        Row(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Box(modifier = modifier.size(256.dp)) {
                NoDataFound()
            }
        }
        SmallSpacer()


        androidx.compose.material3.Text(
            text = "No Data Found",
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
//    Column(
//        modifier = modifier
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Icon(
//            painter = rememberVectorPainter(Icons.Default.Home),
//            contentDescription = null,
//            tint = Red,
//            modifier = modifier
//        )
//        Text(
//            text = stringResource(id = R.string.text_no_data_found),
//            style = MaterialTheme.typography.headlineMedium,
//            textAlign = TextAlign.Center,
//            modifier = modifier
//                .fillMaxWidth()
//        )
//    }
}

@Composable
fun NoDataFound() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.not_found))
    val animationState by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(composition = composition, progress = { animationState })

}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun EmptyPageViewPreview() {
    AppTheme {
        EmptyView()
    }
}
package com.shdclgroup.app.core.base.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.shdclgroup.app.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shdclgroup.app.core.base.component.SmallSpacer
import com.shdclgroup.app.core.domain.error.ExceptionModel
import com.shdclgroup.app.core.theme.AppTheme


fun Throwable.handleThrowable(): String {
    return when (this) {
        is ExceptionModel.Http.Custom -> {
            this.errorBody ?: ""
        }

        else -> {
            this.printStackTrace()

            if (this.localizedMessage != null) {
                this.localizedMessage
            } else throw this

        }
    }
}

@Composable
fun Warning() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.warning))
    val animationState by animateLottieCompositionAsState(
        composition = composition, isPlaying = false, iterations = LottieConstants.IterateForever
    )

    LottieAnimation(composition = composition, progress = { animationState })
}

@Suppress("ForbiddenComment")
@Composable
fun ErrorView(
    navController: NavController? = null,
    modifier: Modifier = Modifier,
    e: Throwable,
    action: () -> Unit
) {
    var errorMsg = "Unknown Error"
    try {
        errorMsg = e.handleThrowable()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {

//        Row(
//            modifier = modifier
//                .align(CenterHorizontally)
//                .wrapContentWidth(Alignment.CenterHorizontally)
//        ) {
//            Box(modifier = modifier.size(200.dp)) {
//                Warning()
//            }
//        }
//        Icon(
////            painter = rememberVectorPainter(Icons.Default.AccountCircle),
//            painter = painterResource(id = R.drawable.ic_error),
//
//            contentDescription = null,
//            tint = Red,
//            modifier = modifier
//                .fillMaxWidth()
//                .wrapContentSize(Alignment.Center)
//        )
//        SmallSpacer()


        Text(
            text = errorMsg,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
        SmallSpacer()
        OutlinedButton(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center), onClick = action
        ) {
            Text(text = "Retry")
        }


    }
}


@Preview(
    showBackground = true, name = "Light Mode"
)
@Preview(
    showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode"
)
@Composable
fun ErrorPageViewPreview() {
    AppTheme {
        ErrorView(e = Exception(), navController = null) {}
    }
}
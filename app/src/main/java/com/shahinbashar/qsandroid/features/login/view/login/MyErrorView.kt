package com.shahinbashar.qsandroid.login.view.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shahinbashar.qsandroid.R
import com.shahinbashar.qsandroid.core.base.component.SmallSpacer
import com.shahinbashar.qsandroid.core.base.widget.handleThrowable
import com.shahinbashar.qsandroid.core.ui.AppSpacer


val APPROVAL_REQUIRED = listOf("identity.devicewaitingforapproval", "identity.devicenotvalidated")


@Composable
fun MyErrorView(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    e: Throwable,
    resetInput: () -> Unit
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


        if(APPROVAL_REQUIRED.any { errorMsg.contains(it) }) {
           DeviceValidation(action = resetInput, navController = navController)

        }
        else  {
            Row(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Box(modifier = modifier.size(180.dp)) {
                    BaseWarning()
                }
            }
            Text(
                text = errorMsg,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            )

            OutlinedButton(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                onClick = resetInput
            ) {
                Text(text = stringResource(id = R.string.text_retry))
            }
        }

    }

}

@Composable
fun BaseWarning() {
    Text(text = "Error")
}

@Composable
fun DeviceValidation(
    modifier: Modifier = Modifier,
    action: () -> Unit,
    navController: NavController? = null
) {
    Column {
        Row(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Box(modifier = modifier.size(200.dp)) {
                Warning()
            }
        }
        Text(
            text = stringResource(id = R.string.device_waiting_for_approval),
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )

        SmallSpacer()
        Button(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            onClick = action
        ) {
            Text(text = stringResource(id = R.string.text_retry))
        }
    }
}

@Composable
fun Warning() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.contact_admin))
    val animationState by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(composition = composition, progress = { animationState })
}

@Composable
fun BaseAnimation(
    lottieResource: Int,
){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieResource))
    val animationState by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(composition = composition, progress = { animationState })
}

@Composable
fun BaseAnimationPlaceHolder(
    modifier: Modifier = Modifier,
    lottieResource: Int,
    text:String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        Row(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .wrapContentWidth(Alignment.CenterHorizontally)

        ) {
            Box(modifier = modifier.size(200.dp)) {
//                Warning()
                BaseAnimation(lottieResource = lottieResource)
            }

        }
        AppSpacer()
        Text(
            text = text,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

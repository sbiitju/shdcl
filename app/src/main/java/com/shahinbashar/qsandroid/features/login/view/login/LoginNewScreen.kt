package com.shahinbashar.qsandroid.features.login.view.login;

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bs23.msfa.login.view.login.PasswordTextField
import com.shahinbashar.qsandroid.R
import com.shahinbashar.qsandroid.core.base.widget.BaseViewState
import com.shahinbashar.qsandroid.core.base.widget.EmptyView
import com.shahinbashar.qsandroid.core.base.widget.LoadingView
import com.shahinbashar.qsandroid.core.extensions.cast
import com.shahinbashar.qsandroid.features.login.view.LoginNewEvent
import com.shahinbashar.qsandroid.features.login.view.LoginNewViewModel
import com.shahinbashar.qsandroid.features.login.view.LoginNewViewState
import com.shahinbashar.qsandroid.login.view.login.MyErrorView
import com.shahinbashar.qsandroid.nav_routes
import com.shahinbashar.qsandroid.prefs
import timber.log.Timber


@Composable
fun LoginNewScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginNewViewModel = hiltViewModel()
) {
    Timber.d("android uid: ${viewModel.androidId(LocalContext.current)}")

    if (prefs.loginModel.isLoggedIn) {
        LaunchedEffect(key1 = viewModel) {
            openDashboardScreen(navController)
        }
    }
    val uiState by viewModel.uiState.collectAsState()

    viewModel.onTriggerEvent(LoginNewEvent.Init)

    LoginNewBody(navController, viewModel, modifier) { padding ->
        LoginNewPage(uiState, viewModel, modifier.padding(padding), navController)
    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LoginNewBody(
    navController: NavController,
    viewModel: LoginNewViewModel,
    modifier: Modifier = Modifier,
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(content = { pageContent.invoke(it) })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginNewPage(
    uiState: BaseViewState<*>,
    viewModel: LoginNewViewModel,
    modifier: Modifier,
    navController: NavController,
) {
    val context = LocalContext.current
    when (uiState) {
        is BaseViewState.Loading -> {
            LoadingView()
        }

        is BaseViewState.Empty -> {
            EmptyView()
        }

        is BaseViewState.Error -> {


            MyErrorView(navController = navController,
                e = uiState.cast<BaseViewState.Error>().throwable,
                resetInput = {
                    viewModel.onTriggerEvent(LoginNewEvent.ProcessLogin(context))
//                    viewModel.onTriggerEvent(LoginNewEvent.Init)
                })
        }

        is BaseViewState.Data -> {
            val data = uiState.cast<BaseViewState.Data<LoginNewViewState>>().value
            if (data.isOpenDashboard) {
                LaunchedEffect(key1 = viewModel) {
                    openDashboardScreen(navController)
                }
            }
            LoginNewContent(
                data,
                viewModel,
                modifier,
            )
        }
    }
}

fun openDashboardScreen(navController: NavController) {
    navController.navigate(nav_routes.HOME) {
        popUpTo(nav_routes.LOGIN) {
            inclusive = true
        }
    }
}

@Composable
fun LoginNewContent(
    data: LoginNewViewState,
    viewModel: LoginNewViewModel,
    modifier: Modifier,
) {

    val context = LocalContext.current
    NewLogin(
        data = data,
        onEvent = { viewModel.onTriggerEvent(it) },
        onLoginClicked = { viewModel.onTriggerEvent(LoginNewEvent.ProcessLogin(context)) },
        onUserNameChanged = { viewModel.onUserChanged(it) },
        onPasswordChanged = { viewModel.onPasswordChanged(it) },
    )

}


@Composable
fun NewLogin(
    modifier: Modifier = Modifier,
    data: LoginNewViewState,
    onEvent: (LoginNewEvent) -> Unit,
    onLoginClicked: () -> Unit,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(all = 24.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp)
                .layoutId(LOGO_CONSTRAIN),
            painter = painterResource(id = R.drawable.premium_sheba),
            contentDescription = ""
        )
        Text(
            text = "Login", fontWeight = FontWeight.Bold, fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(25.dp))
        InputTextField(
            value = data.userName,
            label = "User Name",
            onValueChange = onUserNameChanged,
        )
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(
            value = data.password,
            label = "Password",
            onValueChange = onPasswordChanged,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
        ) {
            Checkbox(modifier = Modifier.align(Alignment.CenterVertically),
                checked = data.isRememberMe,
                onCheckedChange = {
                    onEvent(LoginNewEvent.OnRememberMeClicked)
                })
            Text(
                text = "Remember Me",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
                    .clickable {
                        onEvent(LoginNewEvent.OnRememberMeClicked)
                    },
                textAlign = TextAlign.Start,
                color = Color(0xFF0060AD),
                fontWeight = FontWeight.W500,
                fontSize = 16.sp
            )
        }


        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(onClick = onLoginClicked, buttonText = "Login")

        // Version name & code to the bottom of the screen
        Text(
            text = "Version: ${com.shahinbashar.qsandroid.BuildConfig.VERSION_NAME} (${com.shahinbashar.qsandroid.BuildConfig.VERSION_CODE})",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.W100,
            fontSize = 12.sp
        )


    }

}

@Composable
fun PrimaryButton(onClick: () -> Unit, buttonText: String) {
    Button(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(
            text = buttonText, textAlign = TextAlign.Center
        )
    }
}


@Composable
fun ChangePasswordFields(
    newPassword: String,
    onNewPasswordChanged: (String) -> Unit,
    submitChangePassword: () -> Unit,
) {
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            label = { Text("New Password") },
            value = newPassword,
            onValueChange = onNewPasswordChanged
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = submitChangePassword, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Change Password")
        }
    }
}

@SuppressLint("HardwareIds")
private fun androidId(context: Context): String {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID);
}

private fun getDeviceName(): String {
    return Build.MODEL.toString()
}


const val LOGO_CONSTRAIN = "logo_constrain"
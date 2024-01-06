package com.shahinbashar.qsandroid.features.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shahinbashar.qsandroid.R
import com.shahinbashar.qsandroid.core.base.widget.BaseViewState
import com.shahinbashar.qsandroid.core.base.widget.EmptyView
import com.shahinbashar.qsandroid.core.base.widget.LoadingView
import com.shahinbashar.qsandroid.core.extensions.cast
import com.shahinbashar.qsandroid.core.ui.AppButton
import com.shahinbashar.qsandroid.core.ui.AppDatePicker
import com.shahinbashar.qsandroid.core.ui.AppSingleSelect
import com.shahinbashar.qsandroid.core.ui.AppTextField
import com.shahinbashar.qsandroid.core.ui.formatToDateString
import com.shahinbashar.qsandroid.core.ui.toDate
import com.shahinbashar.qsandroid.core.util.DropdownItem
import com.shahinbashar.qsandroid.features.home.DashBoardViewModel
import com.shahinbashar.qsandroid.features.home.data.model.NavBarModel
import com.shahinbashar.qsandroid.login.view.login.MyErrorView
import com.shahinbashar.qsandroid.nav_routes

@Composable
fun DashboardScreen(
    navController: NavController, viewModel: DashBoardViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    //make a dashboard screen

//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.surface
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            Text(
//                text = "Dashboard",
//                style = MaterialTheme.typography.headlineSmall
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    prefs.loginModel = LoginModel()
//
//                    navController.navigate(nav_routes.LOGIN) {
//                        popUpTo(nav_routes.HOME) {
//                            inclusive = true
//                        }
//                    }
//                }
//            ) {
//                Text(text = "Logout")
//            }
//        }
//    }

    Surface {
        MyComposeFunction(
            uiState = uiState, navController = navController, viewModel = viewModel
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyComposeFunction(
    uiState: BaseViewState<*>, navController: NavController, viewModel: DashBoardViewModel
) {


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
                    viewModel.onTriggerEvent(DashBoardEvent.OnScreenContentChanged(ScreenContent.Expense))
                })
        }

        is BaseViewState.Data -> {
            val data = uiState.cast<BaseViewState.Data<DashBoardState>>().value

            DashboardContent(
                navController = navController, viewModel = viewModel, uiState = uiState
            )


        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    navController: NavController,
    viewModel: DashBoardViewModel = hiltViewModel(),
    uiState: BaseViewState<*>
) {
    val items = listOf(
        NavBarModel(imageVector = Icons.Default.Home, itemName = "Dashboard", onClick = {}, navRoute = nav_routes.HOME),
        NavBarModel(imageVector = Icons.Outlined.Info, itemName = "History", onClick = {}, navRoute = nav_routes.HISTORY),
    )
    val data = uiState.cast<BaseViewState.Data<DashBoardState>>().value
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            if( uiState != BaseViewState.Loading){
                NavigationBar() {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(icon = {
                           Image(imageVector = item.imageVector, contentDescription = null)
                        },
                            label = { Text(item.itemName) },
                            selected = selectedItem == index,
                            onClick = {
                                navController.navigate(item.navRoute)
                            },
                            alwaysShowLabel = true
                        )
                    }
                }
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add ${data.screenContent.name}",
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth()
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White

                )
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MainContent(
                viewModel = viewModel, dashBoardState = data
            )
        }


    }
}

//make a dashboard screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    viewModel: DashBoardViewModel = hiltViewModel(), dashBoardState: DashBoardState
) {
    val uiState by viewModel.uiState.collectAsState()
    val dashBoardState = uiState.cast<BaseViewState.Data<DashBoardState>>().value
    var date by remember { mutableStateOf("Select Date") }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        var openDialog by remember { mutableStateOf(false) }
        var selectedProject:DropdownItem? by remember { mutableStateOf(null) }
        AppSingleSelect(
            title ="Select a project",
            modifier = Modifier.padding(horizontal = 16.dp),
            items = viewModel.data.listOfProject.map { DropdownItem(it._id, it.projectName) },
            selectedItem = selectedProject,
            onSelectionChanged ={
                viewModel.onTriggerEvent(DashBoardEvent.OnProjectIDChanged(it.id))
                selectedProject = it
            }
        )

        OutlinedButton(onClick = { openDialog = true },modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)) {
            Text(text = date)
        }

        AppDatePicker(openDialog = openDialog,
            onDismissRequest = { openDialog = false },
            onDatePicked = {
                it.selectedDateMillis?.toDate()?.let { d ->
                    date = d.formatToDateString()
                    viewModel.onTriggerEvent(DashBoardEvent.OnExpenseDateChanged(date))
                }
            })

        AppTextField(label = "${dashBoardState.screenContent.name} Title", onValueChange = {
            viewModel.onTriggerEvent(DashBoardEvent.OnTitleChanged(it))
        }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
        AppTextField(label = "${dashBoardState.screenContent.name} Amount", onValueChange = {
            viewModel.onTriggerEvent(DashBoardEvent.OnAmountChanged(it))
        }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)

        }

        AppTextField(label = "${dashBoardState.screenContent.name} Description", onValueChange = {
            viewModel.onTriggerEvent(DashBoardEvent.OnDescriptionChanged(it))
        }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ElevatedButton(
                onClick = {

                    viewModel.onTriggerEvent(DashBoardEvent.OnScreenContentChanged(ScreenContent.Expense))
                }, colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = if (dashBoardState.screenContent == ScreenContent.Expense) MaterialTheme.colorScheme.primary else Color.White,
                    contentColor = if (dashBoardState.screenContent == ScreenContent.Expense) Color.White else Color.Black

                ), modifier = Modifier
                    .weight(0.5f)
                    .padding(all = 8.dp)
            ) {
                Text(text = "Expense")
            }
            ElevatedButton(
                onClick = {
                    viewModel.onTriggerEvent(DashBoardEvent.OnScreenContentChanged(ScreenContent.Income))
                }, colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = if (dashBoardState.screenContent == ScreenContent.Income) MaterialTheme.colorScheme.primary else Color.White,
                    contentColor = if (dashBoardState.screenContent == ScreenContent.Income) Color.White else Color.Black
                ), modifier = Modifier
                    .weight(0.5f)
                    .padding(all = 8.dp)

            ) {
                Text(text = "Income")
            }


        }
val context = LocalContext.current
        AppButton(btnTxt = "Submit ${dashBoardState.screenContent.name}") {
            viewModel.onTriggerEvent(
                DashBoardEvent.OnSubmit(
                    amount = viewModel.data.amount,
                    description = viewModel.data.description,
                    projectID = viewModel.data.projectID,
                    userID = viewModel.data.userID,
                    expenseDate = viewModel.data.expenseDate,
                    screenContent = dashBoardState.screenContent,
                    context = context
                )
            )
        }

    }
}


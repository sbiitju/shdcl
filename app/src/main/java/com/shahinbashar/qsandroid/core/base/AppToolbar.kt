package com.shahinbashar.qsandroid.core.base

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.shahinbashar.qsandroid.R


var popupControl by mutableStateOf(false)
var openDialog by mutableStateOf(false)

@Composable
fun ToolbarMenu(){
    val contextForToast = LocalContext.current.applicationContext

    DropdownMenu(
        expanded = popupControl,
        onDismissRequest = {
            popupControl = false
        },
        offset = DpOffset(x = 10.dp, y = (-60).dp)
    ) {
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    Icons.Outlined.Info,
                    contentDescription = null,
//                                tint = MaterialTheme.colorScheme.onSurface,
//                                modifier = Modifier
//                                    .size(48.dp)
//                                    .padding(8.dp)
                )
            },
            text = { DropdownText("Info") },
            onClick = {
                showInformationDialog()
                Toast.makeText(contextForToast, "Refresh Click", Toast.LENGTH_SHORT)
                    .show()
                popupControl = false
            })

//        DropdownMenuItem(
//            text = { DropdownText("Settings") },
//            onClick = {
//                Toast.makeText(contextForToast, "Settings Click", Toast.LENGTH_SHORT)
//                    .show()
//                popupControl = false
//            })
//        DropdownMenuItem(
//            text = { DropdownText("Send Feedback") },
//            onClick = {
//                Toast.makeText(contextForToast, "Send Feedback Click", Toast.LENGTH_SHORT)
//                    .show()
//                popupControl = false
//            })
    }
}


data class ToolbarMenuItem(
    val icon: @Composable () -> Unit = { Icon(Icons.Outlined.Done, contentDescription = null) },
    val text: String,
    val onClick: () -> Unit
)

fun ToolbarMenuFactory(items: List<ToolbarMenuItem>): @Composable () -> Unit {
    return {
        DropdownMenu(
            expanded = popupControl,
            onDismissRequest = {
                popupControl = false
            },
            offset = DpOffset(x = 10.dp, y = (-60).dp)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    leadingIcon = item.icon,
                    text = { DropdownText(text = item.text) },
                    onClick = {
                        item.onClick()
                        popupControl = false
                    }
                )
            }
        }
    }
}

fun showInformationDialog() {
    openDialog = true
}

@Composable
fun InformationDialog() {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog = false
            },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
            title = {
                Text(text = "Title")
            },
            text = {
                Text(
                    "This area typically contains the supportive text " +
                            "which presents the details regarding the Dialog's purpose."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    navController: NavController,
    @StringRes titleResId: Int,
    toolbarMenu: @Composable () -> Unit = { ToolbarMenu() },
) {

//    var popupControl by remember { mutableStateOf(false) }
//    TextButton(onClick = { popupControl = true }) {
//        Text("Open normal popup")
//    }



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ){
        TopAppBar(
            title = {
                Text(
                    stringResource(titleResId),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp)
                    )
                }
            },

            actions = {
//                IconButton(onClick = { popupControl = true }) {
//                    Icon(
//                        painterResource(R.drawable.ic_more),
//                        contentDescription = null,
//                        tint = MaterialTheme.colorScheme.onSurface,
//                        modifier = Modifier
//                            .size(48.dp)
//                            .padding(8.dp)
//                    )
//                }

                IconButton(onClick = { popupControl = true }) {
                    Icon(
                        Icons.Outlined.MoreVert,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp)
                    )
                }

//               ToolbarMenu()
                toolbarMenu()
            },


//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = MaterialTheme.colorScheme.primary)
//        elevation = elevation
        )

//        if (popupControl) {
//            Popup(
//                alignment = Alignment.CenterStart,
//                offset = IntOffset(0, 700),
//                onDismissRequest = { popupControl = false },
//            ) {
//                // Composable content to be shown in the Popup
//                PopupContent()
//            }
//        }

        InformationDialog()
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbarWithNavIcon(
    @StringRes titleResId: Int,
    pressOnBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                stringResource(titleResId),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        navigationIcon = {
            Icon(
                rememberVectorPainter(Icons.Filled.ArrowBack),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { pressOnBack.invoke() }
            )
        },
//        backgroundColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun AppToolbarPreview() {
    AppToolbar(navController = rememberNavController(), titleResId = R.string.app_name)
}
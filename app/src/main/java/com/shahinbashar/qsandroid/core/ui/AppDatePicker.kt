package com.shahinbashar.qsandroid.core.ui

import android.annotation.SuppressLint
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePicker(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onDatePicked: (DatePickerState) -> Unit,
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var selectedDateText by remember { mutableStateOf("") }

    // Fetching current year, month and day
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

// Decoupled snackbar host state from scaffold state for demo purposes.
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier)
// TODO demo how to read the selected date from the state.
    if (openDialog) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
        DatePickerDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDatePicked(datePickerState)
                        onDismissRequest()
//                        snackScope.launch {
//                            snackState.showSnackbar(
//                                "Selected date timestamp: ${datePickerState.selectedDateMillis}"
//                            )
//                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

}

fun Long.toDate(): Date {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar.time
}

private fun Long?.toDateString(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this ?: 0L
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
    return "$dayOfMonth/${month + 1}/$year"
}
fun Date.formatToDateString(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    return dateFormat.format(this)
}


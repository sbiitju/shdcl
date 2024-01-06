package com.shdclgroup.app.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.shdclgroup.app.core.theme.AppTheme


@Composable
fun <T> AppItemPickerDialog(
    openDialog: Boolean,
    items: List<T>,
    onItemSelected: (T) -> Unit,
    itemText: (T) -> String,
    onDialogDismissed: () -> Unit
) {
    val selectedItem = remember { mutableStateOf<T?>(null) }

    if (openDialog) {
        // Show the dialog

        Dialog(onDismissRequest = {
            onDialogDismissed()
        }) {
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            ) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Select an item",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )

                    HairlineDivider()
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        items.forEach { item ->
                            Text(
                                modifier = Modifier
                                    .clickable(onClick = {
                                        selectedItem.value = item
                                        onItemSelected(item)
                                        onDialogDismissed()
                                    })
                                    .padding(start = 32.dp, end = 4.dp, top = 16.dp, bottom = 16.dp)
                                    .fillMaxWidth(),

                                text = itemText(item),
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                ),
                            )
                        }
                    }
                }


            }

        }
    }
}

@Composable
fun HairlineDivider() {
    Divider(
//        color = DividerDefaults.color.copy(alpha = 0.85f),
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f),
        thickness = 0.45.dp,

    )

}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
@Composable
fun ItemPickerDialogPreview() {
    AppTheme() {
        AppItemPickerDialog(
            openDialog = true,
            items = listOf("Item 1", "Item 2", "Item 3"),
            onItemSelected = { item ->
                // Do something with the selected item
            },
            itemText = { item ->
                item
            },
            onDialogDismissed = {
                // Do something when the dialog is dismissed
            }
        )
    }

}
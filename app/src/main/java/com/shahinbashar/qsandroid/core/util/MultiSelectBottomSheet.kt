package com.shahinbashar.qsandroid.core.util

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiSelectBottomSheet(
    openBottomSheet: Boolean,
    items: List<DropdownItem>,
    onCheckedChange: (List<DropdownItem>) -> Unit,
    onDismiss: () -> Unit,
) {

    val localCheckedItems: SnapshotStateList<DropdownItem> =
        remember { mutableStateListOf<DropdownItem>() }

    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    if (openBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onCheckedChange(localCheckedItems.toList())
                onDismiss()
            },
            sheetState = bottomSheetState,
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
//                TextButton(
//                    modifier = Modifier
//                        .padding(4.dp),
//
//                    onClick = {
//                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
//                            if (!bottomSheetState.isVisible) {
//                                onDismiss()
//                            }
//                        }
//                    }
//                ) {
//                    ButtonContent(
//                        text = "Cancel"
//                    )
//                }
                Spacer(modifier = Modifier.size(4.dp))

                Button(
                    modifier = Modifier
                        .padding(4.dp),

                    onClick = {
                        Log.d("dbg", localCheckedItems.toList().toString())
                        onCheckedChange(localCheckedItems.toList())
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                onDismiss()
                            }
                        }
                    }
                ) {
                    ButtonContent(
                        imageVector = Icons.Default.Done,
                        text = "Done"
                    )
                }


            }
            LazyColumn {
                items(items = items) { dropdownItem ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = localCheckedItems.contains(dropdownItem),
                                onClick = {
                                    if (localCheckedItems.contains(dropdownItem)) {
                                        localCheckedItems.remove(dropdownItem)
                                    } else {
                                        localCheckedItems.add(dropdownItem)
                                    }
                                }
                            ),
                        headlineContent = { Text(dropdownItem.toString()) },
                        leadingContent = {
                            Icon(
                                Icons.Default.LocationOn,
                                contentDescription = null,
                            )
                        },
                        trailingContent = {
                            Checkbox(
                                checked = localCheckedItems.contains(dropdownItem),
                                onCheckedChange = null
                            )
                        },
                    )
                }
            }
        }
    }

}



@Preview
@Composable
fun MultiSelectBottomSheetPreview(){
    MultiSelectBottomSheet(
        true,
        listOf(DropdownItem("1","One"), DropdownItem("2","Two"), DropdownItem("3","Three")),
        {},
        {}
    )
}



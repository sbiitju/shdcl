package com.shahinbashar.qsandroid.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.shahinbashar.qsandroid.R
import com.shahinbashar.qsandroid.core.util.DropdownItem

@Composable
fun AppSingleSelect(
    modifier: Modifier = Modifier,
    title: String,
    items: List<DropdownItem>,
    selectedItem: DropdownItem?,
    onSelectionChanged: (DropdownItem) -> Unit
) {
    var showItemPicker by rememberSaveable { mutableStateOf(false) }

    ElevatedCard(modifier = modifier.padding(vertical = dimensionResource(id = R.dimen.item_vertical_padding))) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.column_padding))
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                OutlinedIconButton(onClick = {
                    showItemPicker = true
                }, modifier = Modifier.size(24.dp)) {
                    Icon(
                        Icons.Outlined.Add,
                        contentDescription = "Add Item",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            if(selectedItem != null){
                Spacer(modifier = Modifier.height(16.dp))
            }
            selectedItem?.let{
                ListItem(
                    headlineContent = { Text(selectedItem.toString()) },
                    leadingContent = {
                        Icon(
                            Icons.Default.Place,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )
            }


        }
    }


    AppItemPickerDialog(
        openDialog  = showItemPicker,
        items = items,
        onItemSelected = { item ->
            onSelectionChanged(item)
            showItemPicker = false
        },
        itemText = { item ->
            item.toString()
        },
        onDialogDismissed = {
            showItemPicker = false
        }
    )

}

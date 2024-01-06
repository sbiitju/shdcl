package com.shdclgroup.app.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.shdclgroup.app.core.theme.AppTheme


@Composable
fun AppDialogHero(
    title: String = "Title",
    text: String = "Are you confirm ?",
    @DrawableRes icon: Int? = null,
    iconContentColor: Color = MaterialTheme.colorScheme.secondary,
    openDialog: Boolean,
    onConfirm: () -> Unit, onDismiss: () -> Unit
) {

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            icon = {
                if (icon != null) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = iconContentColor
                    )
                } else {
                    Icon(Icons.Filled.Info, contentDescription = null)
                }
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = text)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlertDialogWithIconSamplePreview() {
    AppTheme {
        AppDialogHero(openDialog = true, onConfirm = {}, onDismiss = {})

    }
}
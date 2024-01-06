package com.shdclgroup.app.features.login.view.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {

    Column() {
        OutlinedTextField(
            value = value,
            maxLines = 1,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp),
            label = {
                Text(text = label)
            },
            placeholder = {
                Text(text = "Enter your $label")
            }
        )
    }
}

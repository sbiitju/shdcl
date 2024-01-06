package com.bs23.msfa.login.view.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    label: String = "Password",
    onValueChange: (String) -> Unit,
) {

    var obsecuredText = remember {
        mutableStateOf(false)
    }
    Column() {
        Text(text = "Password", fontWeight = FontWeight.W400)
        OutlinedTextField(
            value = value,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (obsecuredText.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (obsecuredText.value) {
                    Icon(Icons.Outlined.Lock, contentDescription = "", modifier = Modifier.clickable {
                        obsecuredText.value = !obsecuredText.value
                    })
                } else {
                    Icon(
                        Icons.Filled.Lock,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            obsecuredText.value = !obsecuredText.value
                        })
                }
            },
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

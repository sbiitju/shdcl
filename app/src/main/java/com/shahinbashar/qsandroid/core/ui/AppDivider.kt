package com.shahinbashar.qsandroid.core.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppDivider(){
    Divider(thickness = 0.45.dp)
}

@Composable
fun AppSpacer(){
    Spacer(modifier = Modifier.size(8.dp))
}
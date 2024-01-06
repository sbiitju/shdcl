package com.shdclgroup.app.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(modifier: Modifier=Modifier
    .fillMaxWidth().padding(horizontal = 20.dp),btnTxt:String,btnImg:ImageVector?=null,enable:Boolean=true,onClick:()->Unit){
    Button(onClick =onClick, enabled = enable, modifier = modifier) {
        Text(text = btnTxt)
    }
}
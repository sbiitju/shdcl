package com.shahinbashar.qsandroid.core.util

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shahinbashar.qsandroid.core.ui.AppSpacer

@Composable
fun ButtonContent(
    text: String,
    imageVector: ImageVector? = null,
    @DrawableRes drawable: Int? = null
    ) {
    if(drawable != null){
        Icon(
            painter = painterResource(id = drawable),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        AppSpacer()

    }
    else if(imageVector != null) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
    Text(text = text)
}
package com.shahinbashar.qsandroid.features.home.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarModel(
    val itemName:String,
    val imageVector: ImageVector,
    val onClick:()->Unit,
    val navRoute:String,
)
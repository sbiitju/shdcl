package com.shahinbashar.qsandroid.core.ui

import androidx.compose.runtime.Composable
import com.shahinbashar.qsandroid.core.base.ToolbarMenuFactory
import com.shahinbashar.qsandroid.core.base.ToolbarMenuItem

class AppBar {
}

@Composable
fun AppToolbarOption() {
    ToolbarMenuFactory(
        listOf(
            ToolbarMenuItem(
                text = "Filter",
                onClick = {

                }
            ),


            ToolbarMenuItem(
                text = "Sort",
                onClick = {

                }
            )
        )
    )


}
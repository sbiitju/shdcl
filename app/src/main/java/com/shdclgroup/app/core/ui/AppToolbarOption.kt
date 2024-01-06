package com.shdclgroup.app.core.ui

import androidx.compose.runtime.Composable
import com.shdclgroup.app.core.base.ToolbarMenuFactory
import com.shdclgroup.app.core.base.ToolbarMenuItem

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
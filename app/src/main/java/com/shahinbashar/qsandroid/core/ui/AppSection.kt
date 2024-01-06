package com.shahinbashar.qsandroid.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shahinbashar.qsandroid.R
import com.shahinbashar.qsandroid.core.util.ButtonContent


@Composable
fun SectionTitle(
    text: String,
    isAction: Boolean = false,
    action: () -> Unit = {},
    actionText: String = "Add",
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.horizontal)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(
                color = color,
                fontWeight = FontWeight.SemiBold
            ),
        )

        if (isAction) {
            TextButton(
                onClick = action,
                ) {
                ButtonContent(text = actionText)
            }
        }

    }

}

@Composable
fun SectionTitleVariant(
    text: String,
    isAction: Boolean = false,
    action: () -> Unit = {},
    actionText: String = "Add"
) {
    SectionTitle(
        text = text,
        isAction = isAction,
        action = action,
        actionText = actionText,
        color = MaterialTheme.colorScheme.primary
    )
}


@Composable
fun SectionHeader(
    title: String,
    isFirstSection: Boolean = false,
) {
    if (!isFirstSection) {
        Spacer(modifier = Modifier.height(16.dp))
    } else {
        Spacer(modifier = Modifier.height(8.dp))
    }
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .padding(bottom = 4.dp)
    )
}

@Composable
fun SubSectionHeader(
    title: String,
    isFirstSection: Boolean = false,
    textColor: Color = MaterialTheme.colorScheme.surfaceTint
) {
    if (isFirstSection) {
        Spacer(modifier = Modifier.height(4.dp))
    } else {
        Spacer(modifier = Modifier.height(8.dp))
    }
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier,
        color = textColor
    )
}


@Composable
fun SubText(
    text: String
){
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Thin
        )

    )
}
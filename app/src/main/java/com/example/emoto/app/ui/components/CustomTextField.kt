package com.example.emoto.app.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emoto.R

@Composable
fun CustomTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    supportingText: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholderText: String = "",
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    placeholderStyle: TextStyle = MaterialTheme.typography.labelLarge,
    ) {
    val lightGray = colorResource(R.color.light_gray)
    val gray = colorResource(R.color.gray)
    val darkGray = colorResource(R.color.dark_gray)
    val black = colorResource(R.color.black)
    val white = colorResource(R.color.white)

    val containerColor =
        if (isSystemInDarkTheme())
            darkGray
        else
            lightGray

    val textColor =
        if (isSystemInDarkTheme())
            white
        else
            black

    TextField(
        state = state,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            errorContainerColor = containerColor,
            disabledContainerColor = containerColor,
            errorTextColor = textColor,
            unfocusedTextColor = textColor,
            disabledTextColor = textColor,
            focusedTextColor = textColor,
            errorLabelColor = gray,
            focusedLabelColor = gray,
            disabledLabelColor = gray,
            unfocusedLabelColor = gray,
            errorLeadingIconColor = gray,
            disabledLeadingIconColor = gray,
            errorTrailingIconColor = gray,
            focusedTrailingIconColor = gray,
            focusedLeadingIconColor = gray,
            disabledTrailingIconColor = gray,
            unfocusedLeadingIconColor = gray,
            unfocusedTrailingIconColor = gray,
            errorSupportingTextColor = textColor,
            focusedSupportingTextColor = textColor,
            disabledSupportingTextColor = textColor,
            unfocusedSupportingTextColor = textColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Start),
        shape = MaterialTheme.shapes.medium,
        supportingText = supportingText,
        placeholder = {
            Text(
                text = placeholderText,
                style = placeholderStyle,
                modifier = Modifier
                    .offset(y = 2.dp)
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        lineLimits = lineLimits,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
    )
}

@Composable
@Preview
fun TextFieldPreview() {
    CustomTextField(TextFieldState(), placeholderText = "Введите текст")
}
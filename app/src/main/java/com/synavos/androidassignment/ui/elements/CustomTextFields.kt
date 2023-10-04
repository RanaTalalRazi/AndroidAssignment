package com.synavos.androidassignment.ui.elements

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.synavos.androidassignment.ui.theme.Gray200
import com.synavos.androidassignment.ui.theme.font
import com.synavos.androidassignment.util.nonScaledSp
import com.synavos.androidassignment.util.pSp

@Composable
fun TextFieldPrimary(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 14.pSp),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(12.dp),
    errorMessage: String? = null,
    bgColor: Color = Color.White,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        disabledTextColor = Color.Gray,
        focusedContainerColor = bgColor,
        unfocusedContainerColor = bgColor,
        disabledContainerColor = bgColor,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = Color.Transparent,
        disabledBorderColor = Color.Transparent,
        errorBorderColor = MaterialTheme.colorScheme.error,
    )
) {
    Column(modifier = Modifier) {
        var backgroundColor = MaterialTheme.colorScheme.surface
        if ((!interactionSource.collectIsFocusedAsState().value) || !enabled) {
            backgroundColor = Gray200
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors
        )
        errorMessage?.let {
            val errorStyle = TextStyle(
                color = MaterialTheme.colorScheme.error, fontSize = 10.nonScaledSp
            )
            if (isError) {
                Text(
                    fontFamily = font,
                    text = errorMessage,
                    style = errorStyle,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 14.pSp),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    placeholderText: String = "",
    fontSize: TextUnit = 14.pSp,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    cursorBrush: Brush = SolidColor(Color.Black),
) {

    BasicTextField(modifier = modifier
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(10.dp)),
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        maxLines = maxLines,
        enabled = enabled,
        readOnly = readOnly,
        interactionSource = interactionSource,
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onTextLayout = onTextLayout,
        cursorBrush = cursorBrush,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) Box(
                    Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    leadingIcon()
                }
                Box(Modifier.weight(5f)) {
                    if (value.isEmpty()) CustomText(
                        fontFamily = font,
                        text = placeholderText,
                        style = textStyle,
                        color = Color.LightGray
                    )
                    innerTextField()
                }
                if (trailingIcon != null) Box(Modifier.weight(1f)) { trailingIcon() }
            }
        })
}
package com.example.compo

import android.content.Context
import android.graphics.fonts.FontFamily
import android.graphics.fonts.FontStyle
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    modifier: Modifier,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    inputWrapper: String,
    @StringRes labelResId: Int,
    maxLength: Int,
    maxLines: Int,
    onTextChanged: (String) -> Unit
) {
    var fieldValue by remember { mutableStateOf(inputWrapper) }
    val focusManager = LocalFocusManager.current
    Column {
        OutlinedTextField(
            value = fieldValue,
            label = { Text(stringResource(labelResId), style = MaterialTheme.typography.caption) },
            maxLines = maxLines,
            keyboardOptions = keyboardOptions,
            modifier = modifier,
            onValueChange = {
                if (it.length <= maxLength) {
                    fieldValue = it
                    //text = value.filter { it.isDigit() }
                    onTextChanged(it)
                }
            },
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
                onDone = {
                    focusManager.clearFocus()
                }
            ),
        )

    }
}

@Composable
fun showToast(context: Context, msg: String, duration: Int){

    Toast.makeText(context, msg, duration).show()

}

@Composable
fun customtext(msg: String, fontSize: TextUnit, fontFamily: androidx.compose.ui.text.font.FontFamily?){

    Text(text = msg, fontSize = fontSize, fontFamily = fontFamily)

}

@Composable
fun customButton(btnText : String){

    Button(
        modifier = Modifier
            .wrapContentWidth()
            .background(
                brush = Brush.horizontalGradient(listOf(Color(0xFF28D8A3), Color(0xFF00BEB2))
                ), shape = RectangleShape
            )
            .height(ButtonDefaults.MinHeight),
        onClick = {},
        colors = ButtonDefaults.buttonColors(contentColor = Color.Transparent)
    ) {
        Text(text = btnText)
    }

}

@Composable
fun GradientButton(
    text: String,
    gradient : Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun CustomText14(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = 14.sp,
    fontStyle: androidx.compose.ui.text.font.FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: androidx.compose.ui.text.font.FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {

    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalContentColor.current
        }
    }
    // NOTE(text-perf-review): It might be worthwhile writing a bespoke merge implementation that
    // will avoid reallocating if all of the options here are the defaults
    val mergedStyle = style.merge(
        TextStyle(
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
            lineHeight = lineHeight,
            fontFamily = fontFamily,
            textDecoration = textDecoration,
            fontStyle = fontStyle,
            letterSpacing = letterSpacing
        )
    )
   /* Text(
        text,
        modifier,
        mergedStyle,
        onTextLayout,
        overflow,
        softWrap,
        maxLines,
    )*/
}
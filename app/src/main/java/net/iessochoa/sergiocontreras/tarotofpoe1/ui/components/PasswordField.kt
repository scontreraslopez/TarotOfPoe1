package net.iessochoa.sergiocontreras.tarotofpoe1.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.tarotofpoe1.R
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.Spacing
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.TarotOfPoe1Theme

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.components
 * Created by: Contr
 * On: 23/05/2026 at 22:00
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Composable
fun Password(password: String, isLoginError: Boolean, updatePassword: (String) -> Unit) {

    var passVisibility by rememberSaveable { mutableStateOf(false) }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Spacing.xl,
                vertical = Spacing.sm
            ),
        value = password,
        onValueChange = { updatePassword(it) },
        placeholder = {
            Text(
                text = stringResource(R.string.password),
                color = Color.Gray,
                fontStyle = FontStyle.Italic
            )
        },
        trailingIcon = {
            val eyeIcon = if (passVisibility) R.drawable.outline_visibility_24
            else R.drawable.outline_visibility_off_24
            Icon(
                painter = painterResource(eyeIcon),
                contentDescription = if (passVisibility) "Password is visible"
                else "Password is hidden",
                modifier = Modifier.clickable { passVisibility = !passVisibility })
        },
        visualTransformation = if (passVisibility) PasswordVisualTransformation()
        else VisualTransformation.None,
        isError = isLoginError,
        supportingText = {
            if (isLoginError) Text("Email o contraseña incorrectos")
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordPreview() {
    TarotOfPoe1Theme() {
        Password(
            password = "123456",
            isLoginError = false,
            updatePassword = {}
        )
    }
}
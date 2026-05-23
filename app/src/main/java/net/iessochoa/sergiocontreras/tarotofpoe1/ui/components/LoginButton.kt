package net.iessochoa.sergiocontreras.tarotofpoe1.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.ComponentSizes
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
fun LoginButton(isLoginEnable: Boolean, doLogin: () -> Unit) {
    Button(
        modifier = Modifier.width(ComponentSizes.buttonWidth),
        onClick = { doLogin() },
        enabled = isLoginEnable
    ) {
        Text("Login")
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginButtonPreview() {
    TarotOfPoe1Theme() {
        LoginButton(
            isLoginEnable = true,
            doLogin = {}
        )
    }
}
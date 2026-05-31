package net.iessochoa.sergiocontreras.tarotofpoe1.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.tarotofpoe1.R
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.TarotOfPoe1Theme

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.components
 * Created by: Contr
 * On: 23/05/2026 at 22:00
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Composable
fun RegisterButton(
    onRegisterButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = {
            onRegisterButtonClick()
        }) {
        Text(stringResource(R.string.register))
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterButtonPreview() {
    TarotOfPoe1Theme() {
        RegisterButton(onRegisterButtonClick = {})
    }
}
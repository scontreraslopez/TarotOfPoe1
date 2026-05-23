package net.iessochoa.sergiocontreras.tarotofpoe1.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun UserName(userName: String, isLoginError: Boolean, updateUserName: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Spacing.xl,
                vertical = Spacing.md
            ),
        value = userName,
        onValueChange = { updateUserName(it) },
        placeholder = {
            Text(
                text = "User name",
                color = Color.Gray,
                fontStyle = FontStyle.Italic
            )
        },
        isError = isLoginError
    )
}

@Preview(showBackground = true)
@Composable
private fun UserNamePreview() {
    TarotOfPoe1Theme() {
        UserName (
            userName = "serconlo",
            isLoginError = false,
            updateUserName = {}
        )
    }
}

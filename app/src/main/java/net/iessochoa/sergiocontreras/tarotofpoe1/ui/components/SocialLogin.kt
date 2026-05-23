package net.iessochoa.sergiocontreras.tarotofpoe1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun SocialLogin() {
    Row(modifier = Modifier.clickable {
        // Acción para loguearse con Google
    }) {
        Image(
            modifier = Modifier
                .size(Spacing.lg),
            painter = painterResource(R.drawable.google_logo),
            contentDescription = "Google logo"
        )
        Text(
            text = stringResource(R.string.login_with_google),
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(horizontal = Spacing.sm)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SocialLoginPreview() {
    TarotOfPoe1Theme() {
        SocialLogin()
    }
}
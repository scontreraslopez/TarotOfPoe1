package net.iessochoa.sergiocontreras.tarotofpoe1.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.tarotofpoe1.R
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.TarotOfPoe1Theme

/**
 * Botón de corazón para marcar / desmarcar una carta como favorita.
 *
 * Stateless: recibe [isFavorite] y notifica los cambios por [onToggle].
 * El estado real (persistencia) vive en Room; este composable solo pinta
 * el corazón relleno (favorito) u outline (no favorito).
 *
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.components
 */
@Composable
fun FavoriteToggleButton(
    isFavorite: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = { onToggle() },
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(
                if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite
            ),
            contentDescription = if (isFavorite) {
                "Quitar de favoritos"
            } else {
                "Añadir a favoritos"
            },
            tint = if (isFavorite) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteToggleButtonPreview() {
    TarotOfPoe1Theme {
        // Estado local solo para el preview; en la app vendrá de Room vía ViewModel.
        var favorite by remember { mutableStateOf(false) }
        Row {
            FavoriteToggleButton(isFavorite = favorite, onToggle = { favorite = !favorite })
            FavoriteToggleButton(isFavorite = true, onToggle = {})
        }
    }
}

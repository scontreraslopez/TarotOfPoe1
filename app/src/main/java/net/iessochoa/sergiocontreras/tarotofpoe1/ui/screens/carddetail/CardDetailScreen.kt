package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import net.iessochoa.sergiocontreras.tarotofpoe1.R
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.Acquisition
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCardSamples
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.components.FavoriteToggleButton
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.Spacing
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.TarotOfPoe1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailScreen(
    uiState: CardDetailUiState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(uiState.card?.name ?: "Card") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    // TODO ROOM: sustituir este estado en memoria por el favorito real.
                    //  - isFavorite vendrá del ViewModel (consulta al FavoriteCardDao).
                    //  - onToggle llamará a addFavorite(card.id) / removeFavorite(card.id).
                    //  Solo lo mostramos si hay carta cargada.
                    uiState.card?.let { card ->
                        var isFavorite by remember(card.id) { mutableStateOf(false) }
                        FavoriteToggleButton(
                            isFavorite = isFavorite,
                            onToggle = { isFavorite = !isFavorite },
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        when {
            uiState.isLoading -> LoadingState(Modifier.padding(innerPadding))
            uiState.card == null -> NotFoundState(Modifier.padding(innerPadding))
            else -> CardDetailContent(
                card = uiState.card,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Composable
private fun CardDetailContent(card: DivinationCard, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.sm),
    ) {
        if (card.artUrl != null) {
            AsyncImage(
                model = card.artUrl,
                contentDescription = card.name,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Text(text = card.name, style = MaterialTheme.typography.headlineSmall)
        Text(text = "Stack size: ${card.stackSize}", style = MaterialTheme.typography.bodyMedium)

        Field(label = "Reward", value = card.reward)

        Text(
            text = card.flavourText,
            style = MaterialTheme.typography.bodyLarge,
            fontStyle = FontStyle.Italic,
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = Spacing.sm))

        AcquisitionSection(card.acquisition)
    }
}

@Composable
private fun AcquisitionSection(acquisition: Acquisition) {
    Text(text = "Acquisition", style = MaterialTheme.typography.titleMedium)
    if (acquisition.dropRestricted) {
        Text(text = "Drop restricted", style = MaterialTheme.typography.bodyMedium)
    }
    Field(label = "Drop level", value = acquisition.dropLevel.toString())
    val areas = acquisition.areaRestrictions
    Field(
        label = "Areas",
        value = if (areas.isEmpty()) "Unrestricted" else areas.joinToString(", "),
    )
}

@Composable
private fun Field(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun NotFoundState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Card not found")
    }
}

@Preview(showBackground = true)
@Composable
private fun CardDetailScreenPreview() {

    val cardId = "a-chilling-wind"

    TarotOfPoe1Theme {
        CardDetailScreen(
            uiState = CardDetailUiState(
                card = DivinationCardSamples.all.firstOrNull { it.id == cardId },
                isLoading = false,
            ),
            onBack = {}
        )
    }
}
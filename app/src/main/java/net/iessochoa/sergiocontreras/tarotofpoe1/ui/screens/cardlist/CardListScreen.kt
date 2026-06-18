package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.cardlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCardSamples
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.Spacing
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.TarotOfPoe1Theme

@Composable
fun CardListScreen(
    //viewModel: CardListViewModel,
    uiState: CardListUiState,
    onQueryChange: (String) -> Unit,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    CardListContent(
        uiState = uiState,
        onQueryChange = onQueryChange, //viewModel::onQueryChange,
        onCardClick = onCardClick,
        modifier = modifier,
    )
}

@Composable
private fun CardListContent(
    uiState: CardListUiState,
    onQueryChange: (String) -> Unit,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = uiState.query,
            onValueChange = onQueryChange,
            label = { Text("Search by name or area") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.md),
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                horizontal = Spacing.md,
                vertical = Spacing.sm,
            ),
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
        ) {
            items(uiState.cards, key = { it.id }) { card ->
                CardRow(card = card, onClick = { onCardClick(card.id) })
            }
        }
    }
}

@Composable
private fun CardRow(card: DivinationCard, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.md),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = card.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = card.reward,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = "x${card.stackSize}",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(start = Spacing.sm),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListPreview() {
    TarotOfPoe1Theme {
        CardListContent(
            uiState = CardListUiState(cards = DivinationCardSamples.all, isLoading = false),
            onQueryChange = {},
            onCardClick = {},
        )
    }
}
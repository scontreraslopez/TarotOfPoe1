package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.favorites

import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard

data class FavoritesUiState(
    val cards: List<DivinationCard> = emptyList(),
    val isLoading: Boolean = true,
)

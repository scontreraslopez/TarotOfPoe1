package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.cardlist

import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.cardlist
 * Created by: Contr
 * On: 18/06/2026 at 22:48
 * Creado en Settings -> Editor -> File and Code Templates
 */
data class CardListUiState(
    val query: String = "",
    val cards: List<DivinationCard> = emptyList(),
    val isLoading: Boolean = true,
)
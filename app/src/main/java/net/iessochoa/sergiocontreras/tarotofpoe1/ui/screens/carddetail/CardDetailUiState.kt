package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail

import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail
 * Created by: Contr
 * On: 18/06/2026 at 16:15
 * Creado en Settings -> Editor -> File and Code Templates
 */
data class CardDetailUiState(
    val card: DivinationCard? = null,
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
)

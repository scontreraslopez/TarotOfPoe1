package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.tarotofpoe1.data.repository.CardRepository
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard


class CardDetailViewModel(
    private val repository: CardRepository,
    private val cardId: String,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardDetailUiState())
    val uiState: StateFlow<CardDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = CardDetailUiState(
                card = repository.getCard(cardId),
                isLoading = false,
            )
        }
    }
}
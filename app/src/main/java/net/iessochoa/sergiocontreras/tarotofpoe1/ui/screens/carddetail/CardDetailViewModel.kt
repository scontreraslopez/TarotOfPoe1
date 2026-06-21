package net.iessochoa.sergiocontreras.tarotofpoe1.ui.screens.carddetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.repository.CardRepository
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

    // TODO: Migrar a lambda factory. Eliminar este companion object y en NavigationRoot sustituir
    //  factory = CardDetailViewModel.provideFactory(repository, key.cardId)
    //  por: viewModel(key = key.cardId) { CardDetailViewModel(repository, key.cardId) }
    companion object {
        fun provideFactory(
            repository: CardRepository,
            cardId: String,
        ): ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel> create(modelClass: Class<T>): T =
                CardDetailViewModel(repository, cardId) as T
        }
    }
}
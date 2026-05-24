package com.mirzaev.booklibraryapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirzaev.booklibraryapp.data.BookEntity
import com.mirzaev.booklibraryapp.data.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    val books: StateFlow<List<BookEntity>> = repository.getAllBooks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _event = MutableStateFlow<BookEvent?>(null)
    val event: StateFlow<BookEvent?> = _event

    fun addBook(book: BookEntity) {
        viewModelScope.launch {
            repository.insertBook(book)
        }
    }

    fun updateBook(book: BookEntity) {
        viewModelScope.launch {
            repository.updateBook(book)
        }
    }

    fun deleteBook(id: Int) {
        viewModelScope.launch {
            repository.deleteBook(id)
            _event.value = BookEvent.BookDeleted
        }
    }

    fun onEventConsumed() {
        _event.value = null
    }
}

sealed class BookEvent {
    object BookDeleted : BookEvent()
}
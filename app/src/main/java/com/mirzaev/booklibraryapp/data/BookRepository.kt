package com.mirzaev.booklibraryapp.data

import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {
    fun getAllBooks(): Flow<List<BookEntity>> = bookDao.getAllBooks()

    suspend fun insertBook(book: BookEntity) = bookDao.insert(book)

    suspend fun updateBook(book: BookEntity) = bookDao.update(book)

    suspend fun deleteBook(id: Int) = bookDao.deleteById(id)
}
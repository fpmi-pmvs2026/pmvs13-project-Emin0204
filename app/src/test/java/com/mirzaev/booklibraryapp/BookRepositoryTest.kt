package com.mirzaev.booklibraryapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mirzaev.booklibraryapp.data.BookDatabase
import com.mirzaev.booklibraryapp.data.BookEntity
import com.mirzaev.booklibraryapp.data.BookRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookRepositoryTest {
    private lateinit var database: BookDatabase
    private lateinit var repository: BookRepository

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BookDatabase::class.java
        ).allowMainThreadQueries().build()
        repository = BookRepository(database.bookDao())
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertAndGetBooks() = runBlocking {
        val book = BookEntity(title = "Test", author = "Author", genre = "Fiction")
        repository.insertBook(book)
        val books = repository.getAllBooks().first()
        assert(books.size == 1)
        assert(books[0].title == "Test")
    }

    @Test
    fun testDeleteBook() = runBlocking {
        val book = BookEntity(title = "Delete Me", author = "Author", genre = "Fiction")
        repository.insertBook(book)
        val id = repository.getAllBooks().first()[0].id
        repository.deleteBook(id)
        val books = repository.getAllBooks().first()
        assert(books.isEmpty())
    }
}
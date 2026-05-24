package com.mirzaev.booklibraryapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mirzaev.booklibraryapp.data.BookDatabase
import com.mirzaev.booklibraryapp.data.BookRepository
import com.mirzaev.booklibraryapp.ui.BookListScreen
import com.mirzaev.booklibraryapp.ui.BookViewModel
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var database: BookDatabase
    private lateinit var repository: BookRepository
    private lateinit var viewModel: BookViewModel

    @Test
    fun testEmptyListShowsMessage() {
        // Создаём временную базу в памяти
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BookDatabase::class.java
        ).allowMainThreadQueries().build()

        repository = BookRepository(database.bookDao())
        viewModel = BookViewModel(repository)

        composeTestRule.setContent {
            BookListScreen(
                onAddBook = {},
                onEditBook = {},
                viewModel = viewModel
            )
        }

        composeTestRule.onNodeWithText("Нет книг. Нажмите + для добавления")
            .assertIsDisplayed()
    }

    @After
    fun tearDown() {
        database.close()
    }
}
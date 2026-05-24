package com.mirzaev.booklibraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.mirzaev.booklibraryapp.data.BookDatabase
import com.mirzaev.booklibraryapp.data.BookRepository
import com.mirzaev.booklibraryapp.ui.BookNavGraph
import com.mirzaev.booklibraryapp.ui.BookViewModel
import com.mirzaev.booklibraryapp.ui.BookViewModelFactory
import com.mirzaev.booklibraryapp.ui.theme.BookLibraryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = BookDatabase.getInstance(this)
        val repository = BookRepository(database.bookDao())
        val viewModel = ViewModelProvider(
            this,
            BookViewModelFactory(repository)
        ).get(BookViewModel::class.java)

        enableEdgeToEdge()
        setContent {
            BookLibraryAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BookNavGraph(viewModel = viewModel)
                }
            }
        }
    }
}
package com.mirzaev.booklibraryapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun BookNavGraph(viewModel: BookViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "bookList") {
        composable("bookList") {
            BookListScreen(
                onAddBook = { navController.navigate("addEditBook?bookId=0") },
                onEditBook = { bookId -> navController.navigate("addEditBook?bookId=$bookId") },
                viewModel = viewModel
            )
        }
        composable(
            "addEditBook?bookId={bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId")
            AddEditBookScreen(
                bookId = if (bookId == 0) null else bookId,
                onNavigateBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}
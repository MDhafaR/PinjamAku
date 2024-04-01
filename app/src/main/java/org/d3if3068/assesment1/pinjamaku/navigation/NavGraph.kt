package org.d3if3068.assesment1.pinjamaku.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3068.assesment1.pinjamaku.data.PinjamViewModel
import org.d3if3068.assesment1.pinjamaku.screen.DetailScreen
import org.d3if3068.assesment1.pinjamaku.screen.InputScreen
import org.d3if3068.assesment1.pinjamaku.screen.MainScreen

@Composable
fun NavGraph(viewModel: PinjamViewModel) {
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()

    NavHost(navController= navController, startDestination = "NotesScreen") {
        composable("NotesScreen") {
            MainScreen(
//                state = state,
//                navController = navController,
//                onEvent = viewModel::onEvent
            )
        }
        composable("AddNoteScreen") {
            InputScreen(
//                state = state,
//                navController = navController,
//                onEvent = viewModel::onEvent
            )
        }

        composable("DetailScreen/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            val note = state.notes.find { it.id.toString() == noteId }

            if (note != null) {
                DetailScreen(
//                    state = state,
//                    navController = navController,
//                    noteId = noteId
                )
            } else {
                // Handle case when note is not found
            }
        }

    }
}
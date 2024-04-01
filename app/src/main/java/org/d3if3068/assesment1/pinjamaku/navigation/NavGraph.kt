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

    NavHost(navController= navController, startDestination = "MainScreen") {
        composable("MainScreen") {
            MainScreen(
                state = state,
                navController = navController,
                onEvent = viewModel::onEvent
            )
        }
        composable("InputScreen") {
            InputScreen(
                state = state,
                navController = navController,
                onEvent = viewModel::onEvent
            )
        }

        composable("DetailScreen/{pinjamId}") { backStackEntry ->
            val pinjamId = backStackEntry.arguments?.getString("pinjamId")
            val barang = state.dataPinjam.find { it.id.toString() == pinjamId }

            if (barang != null) {
                DetailScreen(
                    state = state,
                    navController = navController,
                    pinjamId = pinjamId
                )
            } else {
                // Handle case when note is not found
            }
        }

    }
}
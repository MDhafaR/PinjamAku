package org.d3if3068.assesment1.pinjamaku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import org.d3if3068.assesment1.pinjamaku.data.PinjamViewModel
import org.d3if3068.assesment1.pinjamaku.model.DBPinjam
import org.d3if3068.assesment1.pinjamaku.navigation.NavGraph
import org.d3if3068.assesment1.pinjamaku.ui.theme.PinjamAkuTheme

class MainActivity : ComponentActivity() {
//    Assesment 1 | Muhammad Dhafa Ramadhani | 46-04

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            DBPinjam::class.java,
            "PinjamAku.db"
        )
            .build()
    }

    private val viewModel by viewModels<PinjamViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PinjamViewModel(database.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PinjamAkuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(viewModel = viewModel)
                }
            }
        }
    }
}

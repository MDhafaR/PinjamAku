package org.d3if3068.assesment1.pinjamaku.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3068.assesment1.pinjamaku.R
import org.d3if3068.assesment1.pinjamaku.ui.theme.PinjamAkuTheme
import org.d3if3068.assesment1.pinjamaku.ui.theme.Utama

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        Modifier.size(130.dp)
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Utama,
                ),
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.tambahobjputih),
                            contentDescription = "tambah orang",
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
            )
        }
    ) { padding ->
        HalamanUtama(modifier = Modifier.padding(padding))
    }
}

@Composable
fun HalamanUtama(modifier: Modifier) {
    Column(
        modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tambahkan", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Utama
            )
        )
        Text(
            text = "Data Baru", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Utama
            )
        )
        IconButton(onClick = { /*TODO*/ }, Modifier.size(115.dp)) {
            Image(
                painter = painterResource(id = R.drawable.tambahobject),
                contentDescription = "tambah orang",
                modifier = Modifier.size(90.dp)
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPrev() {
    PinjamAkuTheme {
        MainScreen()
    }
}
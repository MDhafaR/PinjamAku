package org.d3if3068.assesment1.pinjamaku.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3068.assesment1.pinjamaku.R
import org.d3if3068.assesment1.pinjamaku.ui.theme.PinjamAkuTheme
import org.d3if3068.assesment1.pinjamaku.ui.theme.Utama
import org.d3if3068.assesment1.pinjamaku.ui.theme.UtamaRingan

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

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp,bottom = 84.dp)
    ) {
        item {
            Box(modifier = Modifier.height(16.dp))
            Text(
                text = "Klik Untuk melihat informasinya....",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Utama
                )
        }
        items(10) {
            Box(modifier = Modifier.height(16.dp))
            ListItem {

            }
        }
    }

//    Column(
//        modifier
//            .fillMaxHeight()
//            .fillMaxWidth(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Tambahkan", style = TextStyle(
//                fontSize = 24.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = Utama
//            )
//        )
//        Text(
//            text = "Data Baru", style = TextStyle(
//                fontSize = 24.sp,
//                fontWeight = FontWeight.SemiBold,
//                color = Utama
//            )
//        )
//        IconButton(onClick = { /*TODO*/ }, Modifier.size(115.dp)) {
//            Image(
//                painter = painterResource(id = R.drawable.tambahobject),
//                contentDescription = "tambah orang",
//                modifier = Modifier.size(90.dp)
//            )
//        }
//    }
}

@Composable
fun ListItem(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Utama, RoundedCornerShape(4.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.dummy_foto),
                contentDescription = stringResource(
                    id = R.string.foto
                ),
                Modifier.size(40.dp)
            )
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "Ini Jeki",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Utama
                )
                Text(
                    text = "Sunlight",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = UtamaRingan
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "40000",
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = UtamaRingan,
                modifier = Modifier.padding(end = 16.dp)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.sampah_hijau),
                    contentDescription = "buang",
                    modifier = Modifier.size(20.dp),
                    Utama
                )
            }
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

@Preview(showBackground = true)
@Composable
fun ListItemPrev() {
    PinjamAkuTheme {
        ListItem {

        }
    }
}
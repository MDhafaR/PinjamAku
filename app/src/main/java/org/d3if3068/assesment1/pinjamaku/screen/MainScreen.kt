package org.d3if3068.assesment1.pinjamaku.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.d3if3068.assesment1.pinjamaku.R
import org.d3if3068.assesment1.pinjamaku.data.PinjamEvent
import org.d3if3068.assesment1.pinjamaku.data.PinjamState
import org.d3if3068.assesment1.pinjamaku.ui.theme.Utama
import org.d3if3068.assesment1.pinjamaku.ui.theme.UtamaRingan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: PinjamState,
    navController: NavController,
    onEvent: (PinjamEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = stringResource(id = R.string.logo),
                        Modifier.size(130.dp)
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Utama,
                ),
                actions = {
                    IconButton(onClick = {
                        state.nama.value = ""
                        state.deskripsi.value = ""
                        state.harga.value = 0
                        navController.navigate("InputScreen")
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.tambahobjputih),
                            contentDescription = stringResource(id = R.string.tambah_orang),
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            state = state,
            navController = navController,
            onEvent = onEvent
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    state: PinjamState,
    navController: NavController,
    onEvent: (PinjamEvent) -> Unit
) {
    if (state.dataPinjam.isEmpty()) {
        TampilanDataKosong(navController)
    } else {
        // Tampilkan daftar catatan jika ada data
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Box(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.info_lanjut),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Utama
                )
            }
            items(state.dataPinjam.size) { index ->
                DataItem(
                    state = state,
                    index = index,
                    onEvent = onEvent,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun TampilanDataKosong(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.tambahkan_data_baru),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Utama
            )
        )
        IconButton(
            onClick = { navController.navigate("InputScreen") },
            modifier = Modifier.size(115.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.tambahobject),
                contentDescription = stringResource(id = R.string.tambahkan_data_baru),
                modifier = Modifier.size(90.dp)
            )
        }
    }
}

@Composable
fun DataItem(
    state: PinjamState,
    index: Int,
    onEvent: (PinjamEvent) -> Unit,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .border(1.dp, Utama, RoundedCornerShape(4.dp))
            .clickable { navController.navigate("DetailScreen/${state.dataPinjam[index].id}") }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.width(200.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.dummy_foto),
                contentDescription = stringResource(R.string.foto),
                Modifier.size(40.dp)
            )


            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = state.dataPinjam[index].nama,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Utama
                )
                Text(
                    text = state.dataPinjam[index].namaBarang,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = UtamaRingan,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val datePinjam = state.dataPinjam[index].tanggalPinjam
            val dateKembali = state.dataPinjam[index].tanggalTempo

            // Calculate total price based on daily price and duration
            fun calculateTotalPrice(pricePerDay: Int, durationInDays: Int): Int {
                return pricePerDay * durationInDays
            }

            // Calculate total price
            val hargaPerHari = state.dataPinjam[index].harga
            val durasiPeminjaman = ((dateKembali - datePinjam) / (24 * 60 * 60 * 1000)).toInt()
            val hargaTotal = calculateTotalPrice(hargaPerHari, durasiPeminjaman)

            Text(
                text = "Rp.$hargaTotal",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = UtamaRingan,
                modifier = Modifier.padding(end = 5.dp)
            )
            IconButton(onClick = { onEvent(PinjamEvent.HapusBarang(state.dataPinjam[index])) }) {
                Icon(
                    painter = painterResource(id = R.drawable.sampah_hijau),
                    contentDescription = stringResource(R.string.hapus),
                    modifier = Modifier.size(20.dp),
                    Utama
                )
            }
        }
    }
}
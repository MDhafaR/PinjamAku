package org.d3if3068.assesment1.pinjamaku.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.d3if3068.assesment1.pinjamaku.R
import org.d3if3068.assesment1.pinjamaku.data.PinjamState
import org.d3if3068.assesment1.pinjamaku.ui.theme.Utama
import org.d3if3068.assesment1.pinjamaku.ui.theme.UtamaBerat
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    pinjamId: String?,
    state: PinjamState,
    navController: NavController
) {
    val note = state.dataPinjam.find { it.id.toString() == pinjamId }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            tint = Color.White,
                            modifier = Modifier.size(35.dp),
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(
                                R.string.kembali,
                            )
                        )
                    }
                },
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        Modifier.size(130.dp)
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Utama,
                )
            )
        }
    ) {padding ->
//            DetailContent(note = note,Modifier.padding(padding))
        if (note != null) {
            DetailContent(noteState = state, modifier = Modifier.padding(padding), productId = pinjamId )
        } else {
            NotFoundContent()
        }
    }
}


@Composable
fun DetailContent(
    noteState: PinjamState,
    productId: String?,
    modifier: Modifier
) {
    val product = noteState.dataPinjam.find { it.id.toString() == productId } // Mendapatkan produk dari indeks yang diberikan

    var datePinjam = product?.tanggalPinjam
    val dateKembali = product?.tanggalTempo

    // Calculate total price based on daily price and duration
    fun calculateTotalPrice(pricePerDay: Int, durationInDays: Int): Int {
        return pricePerDay * durationInDays
    }

// Calculate total price
    val hargaPerHari = product?.harga
    val durasiPeminjaman = ((datePinjam?.let { dateKembali?.minus(it) })?.div((24 * 60 * 60 * 1000)))?.toInt()
    val hargaTotal = hargaPerHari?.let {
        durasiPeminjaman?.let { duration ->
            calculateTotalPrice(it, duration)
        }
    }


    if (product != null) {
        val dateFormat = remember { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }
        val tanggalPinjamFormatted = dateFormat.format(product.tanggalPinjam)
        val tanggalTempoFormatted = dateFormat.format(product.tanggalTempo)

        LazyColumn(
            modifier = modifier.padding(horizontal = 24.dp)
        ) {
            item{
                Box(
                    Modifier.height(16.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dummy_foto),
                        contentDescription = "Foto Produk",
                        Modifier.size(80.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 30.dp)
                    ) {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Utama,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                ) {
                                    append("${product.nama} | ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Utama,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                ) {
                                    append(product.jenisKelamin ?: "-")
                                }
                            }
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = "Rp.$hargaTotal",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = UtamaBerat
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Rp.${product.harga} / hari",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Utama
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = UtamaBerat,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Barang pinjaman : ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Utama,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(product.namaBarang)
                        }
                    }
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Deskripsi :",
                    color = UtamaBerat,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Column(
                    modifier = Modifier
                        .height(240.dp)
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .border(1.dp, Utama, RoundedCornerShape(4.dp))
                        .padding(10.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = product.deskripsi,
                        color = Utama,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = UtamaBerat,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Kontak : ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Utama,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(product.kontak)
                        }
                    }
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = UtamaBerat,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Tanggal pinjam : ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Utama,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(tanggalPinjamFormatted)
                        }
                    }
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = UtamaBerat,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Tanggal tempo : ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Utama,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(tanggalTempoFormatted)
                        }
                    }
                )
                Box(
                    Modifier.height(24.dp)
                )
            }
        }
    } else {
        Text("Produk tidak ditemukan")
    }
}

@Composable
fun NotFoundContent() {
    Text(
        text = "Catatan tidak ditemukan",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        modifier = Modifier.padding(16.dp)
    )
}
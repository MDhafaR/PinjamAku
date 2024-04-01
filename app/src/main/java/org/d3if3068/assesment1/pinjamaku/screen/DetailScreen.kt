package org.d3if3068.assesment1.pinjamaku.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
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
                        contentDescription = stringResource(R.string.logo),
                        Modifier.size(130.dp)
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Utama,
                )
            )
        }
    ) { padding ->
        if (note != null) {
            DetailContent(
                pinjamState = state,
                modifier = Modifier.padding(padding),
                productId = pinjamId
            )
        } else {
            NotFoundContent()
        }
    }
}


@SuppressLint("StringFormatInvalid")
@Composable
fun DetailContent(
    pinjamState: PinjamState,
    productId: String?,
    modifier: Modifier
) {
    val product =
        pinjamState.dataPinjam.find { it.id.toString() == productId } // Mendapatkan produk dari indeks yang diberikan

    val datePinjam = product?.tanggalPinjam
    val dateKembali = product?.tanggalTempo

    // Calculate total price based on daily price and duration
    fun calculateTotalPrice(pricePerDay: Int, durationInDays: Int): Int {
        return pricePerDay * durationInDays
    }

// Calculate total price
    val hargaPerHari = product?.harga
    val durasiPeminjaman =
        ((datePinjam?.let { dateKembali?.minus(it) })?.div((24 * 60 * 60 * 1000)))?.toInt()
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
            item {
                Box(
                    Modifier.height(16.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dummy_foto),
                        contentDescription = stringResource(R.string.foto_produk),
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
                    text = "Rp.${product.harga} / " + stringResource(R.string.hari),
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
                            append(stringResource(id = R.string.barang_pinjaman))
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
                    text = stringResource(R.string.deskripsi),
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
                            append(stringResource(R.string.text_kontak))
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
                            append(stringResource(R.string.tanggal_pinjam))
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
                            append(stringResource(R.string.tanggal_tempo))
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
                val context = LocalContext.current
                Button(
                    modifier = Modifier.padding(top = 24.dp),
                    colors = ButtonDefaults.buttonColors(UtamaBerat),
                    onClick = {
                        shareData(
                            context = context,
                            messege = context.getString(
                                R.string.bagikan_tamplate,
                                product.nama,
                                hargaTotal.toString(),
                                product.harga.toString(),
                                product.namaBarang,
                                product.deskripsi,
                                product.kontak,
                                tanggalPinjamFormatted.toString(),
                                tanggalTempoFormatted.toString()
                            )
                        )
                    },
                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
                ) {
                    Text(text = "bagikan")
                }
                Box(
                    Modifier.height(24.dp)
                )
            }
        }
    } else {
        Text(stringResource(R.string.produk_tidak_ditemukan))
    }
}

@SuppressLint("QueryPermissionsNeeded")
private fun shareData(context: Context, messege: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, messege)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Composable
fun NotFoundContent() {
    Text(
        text = stringResource(R.string.catatan_tidak_ditemukan),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Red,
        modifier = Modifier.padding(16.dp)
    )
}
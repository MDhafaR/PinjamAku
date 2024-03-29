package org.d3if3068.assesment1.pinjamaku.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3068.assesment1.pinjamaku.R
import org.d3if3068.assesment1.pinjamaku.ui.theme.PinjamAkuTheme
import org.d3if3068.assesment1.pinjamaku.ui.theme.Utama
import org.d3if3068.assesment1.pinjamaku.ui.theme.UtamaBerat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
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
    ) { padding ->
        ContentDetail(modifier = Modifier.padding(padding))
    }
}

@Composable
fun ContentDetail(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(
            Modifier.height(5.dp)
        )


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.dummy_foto),
                contentDescription = stringResource(
                    id = R.string.foto
                ),
                Modifier.size(80.dp)
            )
            Column(
                modifier = Modifier.padding(start = 30.dp)
            ) {
                Text(
                    text = "Ini Jeki",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Utama
                )
                Text(
                    text = "40000",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = UtamaBerat
                )
            }
        }
        Text(
            text = "10000 / hari",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Utama
        )
        Text(
            buildAnnotatedString {
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
                    append("Sunlight")
                }
            })
        Text(
            text = "Deskripsi :",
            color = UtamaBerat,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Column(
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .border(1.dp, Utama, RoundedCornerShape(4.dp))
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Si jaki ini teman sekolah dulu, saya kenal dia dari sejak smp sesekolah dan sma pun selalu bersama selalu. pokoknya banyak lah ya yang bisa diingat dari si jaki ini.",
                color = Utama,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Text(
            buildAnnotatedString {
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
                    append("081234567892")
                }
            })
        Text(
            buildAnnotatedString {
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
                    append("20 - 12 - 2024")
                }
            })
        Text(
            buildAnnotatedString {
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
                    append("24 - 12 - 2024")
                }
            })


        Box(
            Modifier.height(24.dp)
        )
    }
}

@Preview
@Composable
fun DetailScreenPrev() {
    PinjamAkuTheme {
        DetailScreen()
    }
}
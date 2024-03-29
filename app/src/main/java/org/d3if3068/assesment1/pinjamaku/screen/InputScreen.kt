@file:Suppress("UNUSED_EXPRESSION")

package org.d3if3068.assesment1.pinjamaku.screen

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3068.assesment1.pinjamaku.R
import org.d3if3068.assesment1.pinjamaku.ui.theme.PinjamAkuTheme
import org.d3if3068.assesment1.pinjamaku.ui.theme.Utama
import org.d3if3068.assesment1.pinjamaku.widgets.GenderButton
import org.d3if3068.assesment1.pinjamaku.widgets.Textarea
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen() {
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
                ),
                actions = {
                    Column(
                        modifier = Modifier
                            .clickable {  }
                            .padding(top = 3.dp)
                    ) {
                        IconButton(onClick = { null }) {
                            Image(
                                painter = painterResource(id = R.drawable.save_putih),
                                contentDescription = stringResource(R.string.save),
                                modifier = Modifier.size(25.dp)
                            )
                        }
                        Text(
                            text = "save",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            modifier = Modifier.offset(y = (-15).dp, x = 5.dp)
                        )
                    }
                }
            )
        }
    ) { padding ->
        ContentInput(modifier = Modifier.padding(padding))
    }
}

@Composable
fun ContentInput(modifier: Modifier) {

    var nama by remember { mutableStateOf("") }
    var namaBarang by remember { mutableStateOf("") }
    var kontak by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    val radioOption = listOf(
        stringResource(R.string.pria),
        stringResource(R.string.wanita)
    )
    var gender by remember { mutableStateOf(radioOption[0]) }

    //date picker
    val day: Int
    val month: Int
    val year: Int
    val calendar = Calendar.getInstance()
    day = calendar.get(Calendar.DAY_OF_WEEK)
    month = calendar.get(Calendar.MONTH)
    year = calendar.get(Calendar.YEAR)
    calendar.time = Date()
    val date = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, day: Int ->
            date.value = "$day-$month-$year"
        },
        year,
        month,
        day
    )




    Column(
        modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            Modifier.height(5.dp)
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nama,
            onValueChange = { nama = it },
            placeholder = { Text(text = stringResource(R.string.nama_asli)) },
            label = { Text(text = stringResource(R.string.label_nama)) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Utama,
                unfocusedBorderColor = Utama,
                unfocusedPlaceholderColor = Color.Gray,
                focusedLabelColor = Utama,
                unfocusedLabelColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
        Column(
            modifier = Modifier.border(1.dp, Utama, RoundedCornerShape(4.dp))
        ) {
            Text(
                text = stringResource(R.string.text_jenis_kelamin),
                Modifier.padding(start = 16.dp, top = 8.dp),
                color = Color.Gray
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                radioOption.forEach { text ->
                    GenderButton(
                        label = text,
                        isSelected = gender == text,
                        modifier = Modifier
                            .selectable(
                                selected = gender == text,
                                onClick = { gender = text },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
            }
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = namaBarang,
            onValueChange = { namaBarang = it },
            placeholder = { Text(text = stringResource(R.string.barang_pinjaman)) },
            label = { Text(text = stringResource(R.string.barang)) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Utama,
                unfocusedBorderColor = Utama,
                unfocusedPlaceholderColor = Color.Gray,
                focusedLabelColor = Utama,
                unfocusedLabelColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
        Textarea()
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = kontak,
            onValueChange = { kontak = it },
            placeholder = { Text(text = stringResource(R.string.kontak_telephone)) },
            label = { Text(text = stringResource(R.string.telephone)) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Utama,
                unfocusedBorderColor = Utama,
                unfocusedPlaceholderColor = Color.Gray,
                focusedLabelColor = Utama,
                unfocusedLabelColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = harga,
            onValueChange = { harga = it },
            placeholder = { Text(text = stringResource(R.string.harga)) },
            label = { Text(text = stringResource(R.string.harga)) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Utama,
                unfocusedBorderColor = Utama,
                unfocusedPlaceholderColor = Color.Gray,
                focusedLabelColor = Utama,
                unfocusedLabelColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                Text(
                    text = "/ hari",
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = stringResource(R.string.tanggal_peminjaman),
                color = Utama,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .border(1.dp, Utama, RoundedCornerShape(4.dp))
                    .clickable { datePickerDialog.show() }
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "kalender",
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = date.value,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Utama
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Tanggal Pengembalian",
                color = Utama,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .border(1.dp, Utama, RoundedCornerShape(4.dp))
                    .clickable { datePickerDialog.show() }
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "kalender",
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = date.value,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Utama
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Foto Barang",
                color = Utama,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .border(1.dp, Utama, RoundedCornerShape(4.dp))
                    .height(40.dp)
                    .width(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Foto",
                    fontSize = 16.sp,
                    color = Utama,
                    fontWeight = FontWeight.SemiBold)
            }
        }
        Box(
            Modifier.height(16.dp)
        )
    }
}

@Preview
@Composable
fun InputScreenPrev() {
    PinjamAkuTheme {
        InputScreen()
    }
}
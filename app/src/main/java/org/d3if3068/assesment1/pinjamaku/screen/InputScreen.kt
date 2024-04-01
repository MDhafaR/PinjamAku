package org.d3if3068.assesment1.pinjamaku.screen

import android.app.DatePickerDialog
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if3068.assesment1.pinjamaku.R
import org.d3if3068.assesment1.pinjamaku.data.PinjamEvent
import org.d3if3068.assesment1.pinjamaku.data.PinjamState
import org.d3if3068.assesment1.pinjamaku.ui.theme.PinjamAkuTheme
import org.d3if3068.assesment1.pinjamaku.ui.theme.Utama
import org.d3if3068.assesment1.pinjamaku.widgets.GenderButton
import org.d3if3068.assesment1.pinjamaku.widgets.Textarea
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    state: PinjamState,
    navController: NavController,
    onEvent: (PinjamEvent) -> Unit
) {
    val context = LocalContext.current
    var hargaInput by remember { mutableStateOf("") }

    // Error handling variables for each input
    var namaError by remember { mutableStateOf(false) }
    var deskripsiError by remember { mutableStateOf(false) }
    var jenisKelaminError by remember { mutableStateOf(false) }
    var hargaError by remember { mutableStateOf(false) }
    var barangError by remember { mutableStateOf(false) }
    var telephonError by remember { mutableStateOf(false) }
    var tanggalPinjamError by remember { mutableStateOf(false) }
    var tanggalTempoError by remember { mutableStateOf(false) }

    // Function to check if any input is blank and update error variables accordingly
    fun checkErrors() {
        namaError = state.nama.value.isBlank()
        deskripsiError = state.deskripsi.value.isBlank()
        jenisKelaminError = state.selectedJenisKelamin.value.isNullOrBlank()
        hargaError = hargaInput.isBlank()
        barangError = state.namaBarang.value.isBlank()
        telephonError = state.kontak.value.isBlank()
        tanggalPinjamError = state.tanggalPinjam.value == 0L
        tanggalTempoError = state.tanggalTempo.value == 0L
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Utama,
                ),
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        Modifier.size(130.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
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
                actions = {
                    Column(
                        modifier = Modifier.clickable {
                            val harga = hargaInput.toIntOrNull()
                                ?: 0 // Mengonversi harga input ke integer, default value 0 jika konversi gagal
                            state.harga.value = harga

                            // Error handling
                            checkErrors()
                            if (namaError || deskripsiError || jenisKelaminError || hargaError || barangError || telephonError || tanggalPinjamError || tanggalTempoError) {
                                // Jika ada error, tampilkan pesan kesalahan atau lakukan tindakan sesuai kebutuhan
                                // Misalnya, menampilkan snackbar atau melakukan navigasi ke halaman lain
                                // Di sini saya hanya mencetak pesan kesalahan untuk demonstrasi
                                println("Ada kesalahan pada input")
                            } else {
                                // Jika tidak ada error, lanjutkan dengan menyimpan catatan
                                onEvent(
                                    PinjamEvent.SimpanBarang(
                                        nama = state.nama.value,
                                        deskripsi = state.deskripsi.value,
                                        harga = state.harga.value,
                                        jenisKelamin = state.selectedJenisKelamin.value,
                                        namaBarang = state.namaBarang.value,
                                        kontak = state.kontak.value,
                                        tanggalPinjam = state.tanggalPinjam.value,
                                        tanggalTempo = state.tanggalTempo.value,
                                        imageUri = state.selectedImageUri.value
                                    )
                                )
                                navController.popBackStack()
                            }
                        }
                    ) {
                        IconButton(onClick = {
                            val harga = hargaInput.toIntOrNull()
                                ?: 0 // Mengonversi harga input ke integer, default value 0 jika konversi gagal
                            state.harga.value = harga

                            // Error handling
                            checkErrors()
                            if (namaError || deskripsiError || jenisKelaminError || hargaError || barangError || telephonError || tanggalPinjamError || tanggalTempoError) {
                                println("Ada kesalahan pada input")
                            } else {
                                onEvent(
                                    PinjamEvent.SimpanBarang(
                                        nama = state.nama.value,
                                        deskripsi = state.deskripsi.value,
                                        harga = state.harga.value,
                                        jenisKelamin = state.selectedJenisKelamin.value,
                                        namaBarang = state.namaBarang.value,
                                        kontak = state.kontak.value,
                                        tanggalPinjam = state.tanggalPinjam.value,
                                        tanggalTempo = state.tanggalTempo.value,
                                        imageUri = state.selectedImageUri.value
                                    )
                                )
                                navController.popBackStack()
                            }
                        }) { // Tombol simpan
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
    ) { paddingValues ->
        ContentInput(
            state = state,
            hargaInput = hargaInput,
            onHargaInputChange = { hargaInput = it },
            modifier = Modifier.padding(paddingValues),
            onImageSelected = { uri ->
                state.selectedImageUri.value = uri
                Toast.makeText(context, "Gambar telah dipilih", Toast.LENGTH_SHORT).show()
            },
            namaError = namaError,
            deskripsiError = deskripsiError,
            jenisKelaminError = jenisKelaminError,
            hargaError = hargaError,
            barangError = barangError,
            telephonError = telephonError,
            tanggalPinjamError = tanggalPinjamError,
            tanggalTempoError = tanggalTempoError,
        )
    }
}

@Composable
fun ContentInput(
    state: PinjamState,
    hargaInput: String,
    onHargaInputChange: (String) -> Unit,
    modifier: Modifier,
    onImageSelected: (Uri) -> Unit,
    namaError: Boolean,
    deskripsiError: Boolean,
    jenisKelaminError: Boolean,
    hargaError: Boolean,
    barangError: Boolean,
    telephonError: Boolean,
    tanggalPinjamError: Boolean,
    tanggalTempoError: Boolean,
) {
    // Date picker initialization
    val calendar = Calendar.getInstance()
    val datePinjam = state.tanggalPinjam
    val dateKembali = state.tanggalTempo
    val context = LocalContext.current
    val datePickerDialogPinjam = DatePickerDialog(
        context,
        { _, year, month, day ->
            calendar.set(year, month, day)
            datePinjam.value = calendar.timeInMillis
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    val datePickerDialogKembali = DatePickerDialog(
        context,
        { _, year, month, day ->
            calendar.set(year, month, day)
            dateKembali.value = calendar.timeInMillis
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Function to format date from Long to String
    fun formatDate(date: Long): String {
        return if (date == 0L) {
            ""
        } else {
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date(date))
        }
    }

    // Inisialisasi launcher untuk memilih gambar dari galeri
    var showDialog by remember { mutableStateOf(false) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                onImageSelected(uri) // Panggil fungsi callback ketika gambar dipilih
            }
        }

    // Fungsi untuk menangani pemilihan gambar dari galeri
    fun selectImage() {
        showDialog = true
        launcher.launch("image/*") // Membuka galeri untuk memilih gambar
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Fitur Ini Masih Tahap Pengembangan!") },
            text = { Text("Anda yakin tetap ingin mengupload gambar?") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    launcher.launch("image/*")
                }) {
                    Text("Ya")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            value = state.nama.value,
            onValueChange = { state.nama.value = it },
            singleLine = true,
            isError = namaError,
            supportingText = { ErrorHint(isError = namaError) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Utama,
                unfocusedBorderColor = Utama,
                unfocusedPlaceholderColor = Color.Gray,
                focusedLabelColor = Utama,
                unfocusedLabelColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            label = { Text(text = stringResource(R.string.label_nama)) },
            placeholder = { Text(text = stringResource(R.string.nama_asli), fontSize = 13.sp) }
        )

        Textarea(state = state, error = deskripsiError)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .border(
                    width = 1.dp,
                    color = if (jenisKelaminError) Color.Red else Utama,
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Text(
                text = stringResource(id = R.string.text_jenis_kelamin),
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                fontSize = 16.sp,
                color = Color.Gray
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                state.jenisKelaminOptions.forEach { jenisKelamin ->
                    GenderButton(
                        jenisKelamin = jenisKelamin,
                        isSelected = state.selectedJenisKelamin.value == jenisKelamin,
                        onJenisKelaminSelected = { selectedJenisKelamin ->
                            state.selectedJenisKelamin.value = selectedJenisKelamin
                        },
                        error = jenisKelaminError
                    )
                }
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            value = hargaInput,
            onValueChange = { onHargaInputChange(it) },
            singleLine = true,
            isError = hargaError,
            supportingText = { ErrorHint(isError = hargaError) },
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
            label = { Text(text = stringResource(R.string.harga)) },
            placeholder = { Text(text = stringResource(R.string.harga), fontSize = 13.sp) },
            trailingIcon = {
                Text(
                    text = "/ hari",
                    Modifier.padding(end = 16.dp),
                    color = Color.Gray
                )
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            value = state.namaBarang.value,
            onValueChange = { state.namaBarang.value = it },
            singleLine = true,
            isError = barangError,
            supportingText = { ErrorHint(isError = barangError) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Utama,
                unfocusedBorderColor = Utama,
                unfocusedPlaceholderColor = Color.Gray,
                focusedLabelColor = Utama,
                unfocusedLabelColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            label = { Text(text = stringResource(R.string.barang)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.barang_pinjaman),
                    fontSize = 13.sp
                )
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            value = state.kontak.value,
            onValueChange = { state.kontak.value = it },
            singleLine = true,
            isError = telephonError,
            supportingText = { ErrorHint(isError = telephonError) },
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
            label = { Text(text = stringResource(R.string.telephone)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.kontak_telephone),
                    fontSize = 13.sp
                )
            }
        )

        Column(
            modifier = Modifier.padding(top = 8.dp),
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
                    .border(
                        1.dp,
                        if (tanggalPinjamError) Color.Red else Utama,
                        RoundedCornerShape(4.dp)
                    )
                    .clickable { datePickerDialogPinjam.show() }
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "kalender",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = formatDate(datePinjam.value),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Utama
                )
            }
        }

        Column(
            modifier = Modifier.padding(top = 8.dp),
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
                    .border(
                        1.dp,
                        if (tanggalTempoError) Color.Red else Utama,
                        RoundedCornerShape(4.dp)
                    )
                    .clickable { datePickerDialogKembali.show() }
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "kalender",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = formatDate(dateKembali.value),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Utama
                )
            }
        }

        Column(
            modifier = Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Utama,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Foto Barang ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                    ) {
                        append("(Optional)")
                    }
                }
            )
            Row(
                modifier = Modifier
                    .border(1.dp, Utama, RoundedCornerShape(4.dp))
                    .clickable { selectImage() }
                    .height(35.dp)
                    .width(95.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.foto),
                    fontSize = 16.sp,
                    color = Utama,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = "tidak boleh kosong")
    }
}

@Preview
@Composable
fun addBarangPreview() {
    val state = remember { PinjamState() }
    val navController = rememberNavController()

    PinjamAkuTheme {
        InputScreen(
            state = state,
            navController = navController,
            onEvent = {}
        )
    }
}
package org.d3if3068.assesment1.pinjamaku.data

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import org.d3if3068.assesment1.pinjamaku.model.Pinjam

data class PinjamState(

    val notes: List<Pinjam> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf(""),
    val harga: MutableState<Int> = mutableIntStateOf(0),
    val jenisKelaminOptions: List<String> = listOf("Pria", "Wanita"), // Pilihan jenis kelamin
    val selectedJenisKelamin: MutableState<String?> = mutableStateOf(null),
    val namaBarang: MutableState<String> = mutableStateOf(""),
    val kontak: MutableState<String> = mutableStateOf(""),
    val tanggalPinjam: MutableState<Long> = mutableStateOf(0), // Tambahkan state untuk tanggalPinjam
    val tanggalTempo: MutableState<Long> = mutableStateOf(0), // Tambahkan state untuk tanggalTempo
    val selectedImageUri: MutableState<Uri?> = mutableStateOf(null)
)
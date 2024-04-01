package org.d3if3068.assesment1.pinjamaku.data

import android.net.Uri
import org.d3if3068.assesment1.pinjamaku.model.Pinjam

sealed interface PinjamEvent {
    object sortDataPinjam : PinjamEvent

    data class HapusBarang(val pinjam: Pinjam) : PinjamEvent

    data class SimpanBarang(
        val nama: String,
        val deskripsi: String,
        val harga: Int,
        val jenisKelamin: String?, // Menambahkan properti jenis kelamin ke event SaveNote
        val namaBarang: String,
        val kontak: String,
        val tanggalPinjam: Long, // Tambahkan tanggalPinjam
        val tanggalTempo: Long, // Tambahkan tanggalTempo
        val imageUri: Uri?
    ) : PinjamEvent

    data class SelectImageUri(val uri: Uri) : PinjamEvent

    data class SelectJenisKelamin(val jenisKelamin: String) : PinjamEvent // Menambahkan event untuk pemilihan jenis kelamin
}
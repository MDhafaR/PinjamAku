package org.d3if3068.assesment1.pinjamaku.data

import android.net.Uri
import org.d3if3068.assesment1.pinjamaku.model.Pinjam

sealed interface PinjamEvent {
    object SortNotes : PinjamEvent

    data class DeleteNote(val note: Pinjam) : PinjamEvent

    data class SaveNote(
        val title: String,
        val description: String,
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
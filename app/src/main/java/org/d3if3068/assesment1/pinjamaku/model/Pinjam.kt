package org.d3if3068.assesment1.pinjamaku.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pinjam(

    val nama: String,
    val deskripsi: String,
    val harga: Int,
    val jenisKelamin: String? = null,
    val namaBarang: String,
    val kontak: String,
    val tanggalPinjam: Long,
    val tanggalTempo: Long,
    val imageUri: String? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
package org.d3if3068.assesment1.pinjamaku.data

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.d3if3068.assesment1.pinjamaku.model.DaoPinjam
import org.d3if3068.assesment1.pinjamaku.model.Pinjam

class PinjamViewModel(
    private val dao: DaoPinjam
) : ViewModel() {

    private val isSortedByDateAdded = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    private var dataPeminjaman =
        isSortedByDateAdded.flatMapLatest { _ ->
            dao.getDataPinjamOrderdByTitle()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(PinjamState())
    val state =
        combine(_state, isSortedByDateAdded, dataPeminjaman) { state, _, dataPeminjaman ->
            state.copy(
                dataPinjam = dataPeminjaman
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PinjamState())

    fun onEvent(event: PinjamEvent) {
        when (event) {
            is PinjamEvent.HapusBarang -> {
                viewModelScope.launch {
                    dao.deleteDataPinjam(event.pinjam)
                }
            }

            is PinjamEvent.SimpanBarang -> {

                val pinjam = Pinjam(
                    nama = state.value.nama.value,
                    deskripsi = state.value.deskripsi.value,
                    harga = state.value.harga.value,
                    namaBarang = state.value.namaBarang.value,
                    kontak = state.value.kontak.value,
                    jenisKelamin = state.value.selectedJenisKelamin.value
                        ?: "", // Mengambil jenis kelamin yang dipilih
                    tanggalPinjam = state.value.tanggalPinjam.value, // Mengambil tanggalPinjam dari state
                    tanggalTempo = state.value.tanggalTempo.value,// Mengambil tanggalTempo dari state
                    imageUri = state.value.selectedImageUri.value.toString()
                )

                viewModelScope.launch {
                    dao.upsertDataPinjam(pinjam)
                }

                _state.update {
                    it.copy(
                        nama = mutableStateOf(""),
                        deskripsi = mutableStateOf(""),
                        harga = mutableIntStateOf(0),
                        selectedJenisKelamin = mutableStateOf(null), // Mengatur kembali jenis kelamin yang dipilih menjadi null setelah menyimpan
                        namaBarang = mutableStateOf(""),
                        kontak = mutableStateOf(""),
                        tanggalPinjam = mutableLongStateOf(0), // Mengatur kembali tanggalPinjam
                        tanggalTempo = mutableLongStateOf(0),
                        selectedImageUri = mutableStateOf(null)
                    )
                }
            }

            PinjamEvent.SortDataPinjam -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }

            is PinjamEvent.SelectJenisKelamin -> {
                _state.update {
                    it.copy(
                        selectedJenisKelamin = mutableStateOf(event.jenisKelamin) // Menyimpan jenis kelamin yang dipilih ke dalam state
                    )
                }
            }

            is PinjamEvent.SelectImageUri -> {
                _state.update {
                    it.copy(
                        selectedImageUri = mutableStateOf(event.uri) // Menyimpan URI gambar yang dipilih ke dalam state
                    )
                }
            }
        }
    }


}
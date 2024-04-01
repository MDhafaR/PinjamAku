package org.d3if3068.assesment1.pinjamaku.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoPinjam {

    @Upsert
    suspend fun upsertDataPinjam(pinjam: Pinjam)

    @Delete
    suspend fun deleteDataPinjam(pinjam: Pinjam)

    @Query("SELECT * FROM pinjam ORDER BY nama ASC")
    fun getDataPinjamOrderdByTitle(): Flow<List<Pinjam>>

}
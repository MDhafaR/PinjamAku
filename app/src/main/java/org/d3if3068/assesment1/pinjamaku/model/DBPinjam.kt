package org.d3if3068.assesment1.pinjamaku.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Pinjam::class], version = 1)
abstract class DBPinjam : RoomDatabase() {
    abstract val dao: DaoPinjam
}
package org.d3if3068.assesment1.pinjamaku.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Pinjam::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract val dao: DaoPinjam

//    companion object {
//        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE note ADD COLUMN harga INTEGER NOT NULL DEFAULT 0")
//            }
//        }
//    }

    //    companion object {
//        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE note ADD COLUMN jenisKelamin TEXT DEFAULT NULL") // Tambahkan kolom jenis_kelamin dengan tipe data TEXT
//            }
//        }
//    }
//}
//    companion object {
//        val MIGRATION_3_4: Migration = object : Migration(3, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // Tambahkan kolom namaBarang dengan tipe data TEXT dan nilai default kosong ('')
//                database.execSQL("ALTER TABLE note ADD COLUMN namaBarang TEXT NOT NULL DEFAULT ''")
//            }
//        }
//    }
//    companion object{
//        val MIGRATION_4_5: Migration = object : Migration(4, 5) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // Tambahkan kolom namaBarang dengan tipe data TEXT dan nilai default kosong ('')
//                database.execSQL("ALTER TABLE note ADD COLUMN kontak TEXT NOT NULL DEFAULT ''")
//            }
//        }
//    }
//    companion object{
//        val MIGRATION_5_6: Migration = object : Migration(5, 6) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE note ADD COLUMN tanggalPinjam INTEGER NOT NULL DEFAULT 0") // Tambahkan kolom tanggalPinjam
//                database.execSQL("ALTER TABLE note ADD COLUMN tanggalTempo INTEGER NOT NULL DEFAULT 0") // Tambahkan kolom tanggalTempo
//            }
//        }
//    }

//    companion object {
//        val MIGRATION_6_7: Migration = object : Migration(6, 7) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE note ADD COLUMN imageUri TEXT DEFAULT NULL")
//            }
//        }
//    }
}
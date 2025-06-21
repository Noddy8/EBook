package com.noddy.book

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.noddy.ebook.daos.BookmarksDao
import com.noddy.ebook.daos.NotesDao
import com.noddy.ebook.entities.BookmarksEntity
import com.noddy.ebook.entities.NotesEntity

@Database(
    entities = [BookmarksEntity::class, NotesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarksDao(): BookmarksDao
    abstract fun notesDao(): NotesDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "books_db"
                    ).build()
                }
            }
            return INSTANCE
        }


    }


}




















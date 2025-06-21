package com.noddy.ebook.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.noddy.ebook.entities.BookmarksEntity
import com.noddy.ebook.entities.NotesEntity

@Dao
interface NotesDao {

    @Query("SELECT * from notes WHERE bookId == :bookId")
    suspend fun getNotes(bookId: String): List<NotesEntity>

    @Insert
    suspend fun addNote(entity: NotesEntity)

}
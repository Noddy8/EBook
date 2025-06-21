package com.noddy.book

import com.folioreader.FolioReader
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.noddy.book.databinding.ActivityReaderBinding

class ReaderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReaderBinding
    private lateinit var folioReader: FolioReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val epubPath = intent.getStringExtra("book_epub")
//        folioReader = FolioReader.get()
//        folioReader.openBook(epubPath)
    }

    override fun onDestroy() {
        super.onDestroy()
        folioReader.close()
    }
}
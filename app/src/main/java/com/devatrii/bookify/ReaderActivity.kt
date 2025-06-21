package com.noddy.ebook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.noddy.ebook.databinding.ActivityReaderBinding
import com.folioreader.FolioReader

class ReaderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReaderBinding
    private lateinit var folioReader: FolioReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val epubPath = intent.getStringExtra("book_epub")
        folioReader = FolioReader.get()
        folioReader.openBook(epubPath)
    }

    override fun onDestroy() {
        super.onDestroy()
        folioReader.close()
    }
}
package com.noddy.ebook.ViewModels

import androidx.lifecycle.ViewModel
import com.noddy.ebook.Repository.BookRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(val repo: BookRepo) : ViewModel() {

    val downloadLiveData get() = repo.downloadLiveData

    fun downloadFile(url: String, fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.downloadPdf(
                url = url,
                fileName = fileName
            )
        }
    }

}
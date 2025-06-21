package com.noddy.ebook.Models

import com.noddy.ebook.Adapters.LAYOUT_HOME


data class HomeModel(
    val catTitle: String? = null,
    val booksList: ArrayList<BooksModel>? = null,

    val bod: BooksModel? = null,
    val LAYOUT_TYPE: Int = LAYOUT_HOME
)

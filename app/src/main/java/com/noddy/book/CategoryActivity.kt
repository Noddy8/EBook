package com.noddy.book

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.noddy.book.databinding.ActivityCategoryBinding
import com.noddy.ebook.Adapters.CategoryAdapter
import com.noddy.ebook.Models.BooksModel
import com.noddy.ebook.Utils.SpringScrollHelper

class CategoryActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    private val activity = this

    private val list = ArrayList<BooksModel>()
    private val adapter = CategoryAdapter(list, activity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            mRvCategory.adapter = adapter
            SpringScrollHelper().attachToRecyclerView(mRvCategory)
            val bookList = intent.getSerializableExtra("book_list") as ArrayList<BooksModel>
            bookList.forEach {
                list.add(it)
            }
        }
    }

    override fun onBackPressed() {
        finish()
        with(window) {
            sharedElementReenterTransition = null
            sharedElementReturnTransition = null
        }
        binding.mRvCategory.transitionName = null
    }
}
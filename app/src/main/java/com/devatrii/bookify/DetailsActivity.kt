package com.noddy.ebook

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.noddy.ebook.Models.BooksModel
import com.noddy.ebook.Repository.BookRepo
import com.noddy.ebook.Utils.MyResponses
import com.noddy.ebook.Utils.loadAndShowInterstitialAdWithProgressDialog
import com.noddy.ebook.Utils.loadOnline
import com.noddy.ebook.ViewModels.BookViewModel
import com.noddy.ebook.ViewModels.BookViewModelFactory
import com.noddy.ebook.databinding.ActivityDetailsBinding
import com.noddy.ebook.databinding.LayoutProgressBinding

class DetailsActivity : AppCompatActivity() {
    val activity = this
    lateinit var binding: ActivityDetailsBinding

    private val repo = BookRepo(activity)
    private val viewModel by lazy {
        ViewModelProvider(
            activity,
            BookViewModelFactory(repo)
        )[BookViewModel::class.java]
    }

    private val TAG = "Details_Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bookModel = intent.getSerializableExtra("book_model") as BooksModel

        binding.apply {
            bookModel.apply {
                mBookTitle.text = title
                mAuthorName.text = author
                mBookDesc.text = description
                mBookImage.loadOnline(image)
            }

            mReadBookBtn.setOnClickListener {
                viewModel.downloadFile(bookModel.epubUrl, bookModel.title)
            }
            val dialogBinding = LayoutProgressBinding.inflate(layoutInflater)
            val dialog = Dialog(activity).apply {
                setCancelable(false)
                setContentView(dialogBinding.root)
                this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                this.window!!.setLayout(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
            }

            viewModel.downloadLiveData.observe(activity) {
                when (it) {
                    is MyResponses.Error -> {
                        dialog.dismiss()
                        Log.e(TAG, "onCreate: ${it.errorMessage}")
                    }

                    is MyResponses.Loading -> {
                        dialogBinding.mProgress.text = "${it.progress}%"
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            dialogBinding.mProgressBar.setProgress(it.progress, true)
                        } else {
                            dialogBinding.mProgressBar.progress = it.progress

                        }
                        dialog.show()
                        Log.i(TAG, "onCreate: Progress ${it.progress}")

                    }

                    is MyResponses.Success -> {
                        activity.loadAndShowInterstitialAdWithProgressDialog(customCode = {
                            dialog.dismiss()
                            Log.i(TAG, "onCreate: Downloaded ${it.data}")
                            Intent().apply {
                                putExtra("book_epub", it.data?.filePath)
                                setClass(activity, ReaderActivity::class.java)
                                startActivity(this)
                            }
                        })

                    }
                }
            }

        }

    }
}
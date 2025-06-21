package com.noddy.ebook

import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.noddy.ebook.Utils.loadBannerAd
import com.noddy.ebook.databinding.ActivityPdfBinding
import com.noddy.ebook.fragments.PdfToolsFragment
//import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
//import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
//import com.shockwave.pdfium.PdfDocument

class PdfActivity : AppCompatActivity(){
//class PdfActivity : AppCompatActivity(), OnPageChangeListener, OnLoadCompleteListener {

    private val activity = this
    lateinit var binding: ActivityPdfBinding
    private lateinit var bookPdf: String
    lateinit var bookId: String
    private var uri: Uri? = null
    private var pageNumber = 1
    private lateinit var pdfFileName: String

    companion object {
        private const val TAG = "PdfActivity"
        private const val REQUEST_CODE = 42
        private const val PERMISSION_CODE = 42042
        private const val READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfBinding.inflate(layoutInflater)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        supportActionBar?.hide()
//        setupBasicViews()

        bookPdf = intent.getStringExtra("book_pdf").toString()
        bookId = intent.getStringExtra("book_id").toString()


//        if (ContextCompat.checkSelfPermission(
//                this,
//                READ_EXTERNAL_STORAGE
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE), PERMISSION_CODE)
//        } else {
//            displayFromUri(bookPdf.toUri())
//        }
    }

//    private fun setupBasicViews() {
//        binding.mToolsFab.setOnClickListener {
//            val toolsBottomSheet = PdfToolsFragment()
//            toolsBottomSheet.show(supportFragmentManager, toolsBottomSheet.tag)
//        }
//    }
//
//    private fun displayFromUri(uri: Uri) {
//        this.uri = uri
//        pdfFileName = getFileName(uri)

//        binding.pdfView.fromUri(uri)
//            .defaultPage(pageNumber)
//            .onPageChange(this)
//            .swipeVertical(true)
//            .showMinimap(false)
//            .enableAnnotationRendering(true)
//            .onLoad(this)
//            .load()
    }

//    private fun getFileName(uri: Uri): String {
//        var result: String? = null
//        if (uri.scheme == "content") {
//            val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
//            cursor.use {
////                if (it != null && it.moveToFirst()) {
////                    result = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
////                }
//            }
//        }
//        return result ?: uri.lastPathSegment ?: "Unknown"
//    }
//
//    override fun onPageChanged(page: Int, pageCount: Int) {
//        pageNumber = page
//        title = "$pdfFileName $page / $pageCount"
//    }

//    override fun loadComplete(nbPages: Int) {
////        val meta = binding.pdfView.documentMeta
////        Log.e(TAG, "title = ${meta.title}")
////        Log.e(TAG, "author = ${meta.author}")
////        Log.e(TAG, "subject = ${meta.subject}")
////        Log.e(TAG, "keywords = ${meta.keywords}")
////        Log.e(TAG, "creator = ${meta.creator}")
////        Log.e(TAG, "producer = ${meta.producer}")
////        Log.e(TAG, "creationDate = ${meta.creationDate}")
////        Log.e(TAG, "modDate = ${meta.modDate}")
//
//        printBookmarksTree(binding.pdfView.tableOfContents, "-")
//    }
//
//    private fun printBookmarksTree(tree: List<PdfDocument.Bookmark>, sep: String) {
//        for (b in tree) {
//            Log.e(TAG, "$sep ${b.title}, p ${b.pageIdx}")
//            if (b.hasChildren()) {
//                printBookmarksTree(b.children, "$sep-")
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == PERMISSION_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                uri?.let { displayFromUri(it) }
//            } else {
//                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}

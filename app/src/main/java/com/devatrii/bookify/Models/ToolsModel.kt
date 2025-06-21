package com.noddy.ebook.Models

import androidx.annotation.DrawableRes
import com.noddy.ebook.enums.ToolsType

data class ToolsModel(
    val title: String,
    @DrawableRes
    val image: Int,
    val type: ToolsType
)

package com.tamnn.vnexpressfeeds.util

import java.util.regex.Pattern

object RegexUtil {
    private const val IMG_PATTERN = "<img[^>]+src=\"([^\">]+)\""
    private val imgPattern = Pattern.compile(IMG_PATTERN)

    fun getImageUrl(html: String): String? {
        val matcher = imgPattern.matcher(html)
        if(matcher.find()){
            return matcher.group()
        }
        return null
    }
}
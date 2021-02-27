package com.tamnn.vnexpressfeeds.feature.web

import android.os.Parcel
import android.os.Parcelable
import com.tamnn.vnexpressfeeds.mvp.Screen

class WebScreen(val url: String)
    : Screen {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(url)
    }

    override fun hashCode() = super.hashCode()

    override fun equals(other: Any?) = other is WebScreen && (other === this || (other.url == url))

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<WebScreen> = object : Parcelable.Creator<WebScreen> {
            override fun createFromParcel(parcel: Parcel): WebScreen {
                val url = parcel.readString() ?: ""
                return WebScreen(url)
            }

            override fun newArray(size: Int) = arrayOfNulls<WebScreen?>(size)
        }
    }
}
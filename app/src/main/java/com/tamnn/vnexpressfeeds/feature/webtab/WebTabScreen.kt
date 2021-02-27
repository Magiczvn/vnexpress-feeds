package com.tamnn.vnexpressfeeds.feature.webtab

import android.os.Parcel
import android.os.Parcelable
import com.tamnn.vnexpressfeeds.mvp.Screen


class WebTabScreen(val url: String): Screen {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(url)
    }

    override fun hashCode() = super.hashCode()

    override fun equals(other: Any?) = other is WebTabScreen && (other === this || (other.url == url))



    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<WebTabScreen> = object : Parcelable.Creator<WebTabScreen> {
            override fun createFromParcel(parcel: Parcel): WebTabScreen {
                val url = parcel.readString() ?: ""
                return WebTabScreen(url)
            }

            override fun newArray(size: Int) = arrayOfNulls<WebTabScreen?>(size)
        }
    }
}
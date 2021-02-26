package com.tamnn.vnexpressfeeds.feature.main

import android.os.Parcel
import android.os.Parcelable
import com.tamnn.vnexpressfeeds.mvp.Screen

class MainScreen : Screen {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
    }

    override fun hashCode() = super.hashCode()

    override fun equals(other: Any?) = other is MainScreen

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MainScreen> = object : Parcelable.Creator<MainScreen> {
            override fun createFromParcel(parcel: Parcel) = MainScreen()

            override fun newArray(size: Int) = arrayOfNulls<MainScreen?>(size)
        }
    }
}
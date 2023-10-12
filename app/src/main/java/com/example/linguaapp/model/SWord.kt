package com.example.linguaapp.model

import android.os.Parcel
import android.os.Parcelable

data class SWord(val name: String, var quantity: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SWord> {
        override fun createFromParcel(parcel: Parcel): SWord {
            return SWord(parcel)
        }

        override fun newArray(size: Int): Array<SWord?> {
            return arrayOfNulls(size)
        }
    }
}
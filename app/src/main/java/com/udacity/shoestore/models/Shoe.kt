package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(var name: String, var size: Double, var company: String, var description: String,
                val images: List<String> = mutableListOf()) : Parcelable {

    fun hasEmptyField(): Boolean {
        if (name.isEmpty() || size == 0.0 || company.isEmpty() || description.isEmpty()) return true
        return false
    }
}
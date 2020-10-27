package com.udacity.shoestore.ui

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.BR
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    var shoeItem: Shoe? = null
    private var shoes = mutableListOf<Shoe>()

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    private val _shoeInsertionFinalized = MutableLiveData<Boolean>()
    val shoeInsertionFinalized: LiveData<Boolean>
        get() = _shoeInsertionFinalized

    private val _hasMissedFields = MutableLiveData<Boolean>()
    val hasMissedFields: LiveData<Boolean>
        get() = _hasMissedFields

    fun addShoe() {
        shoeItem?.let { shoe ->
            shoe.size = shoeObservable.shoeSize.ifBlank { "0.0" }.toDouble()
            Timber.i(shoeItem.toString())
            if(shoe.hasEmptyField()) {
                _hasMissedFields.value = true
            } else {
                shoes.add(shoe)
                finalizeInsertion()
                _shoeList.value = shoes
            }
        }
    }

    fun finalizeInsertion() {
        _shoeInsertionFinalized.value = true
    }

    fun onDetailFinished() {
        _shoeInsertionFinalized.value = false
        _hasMissedFields.value = false
    }

    fun prepareInsertion() {
        shoeItem = Shoe("", 0.0, "", "")
        shoeObservable.shoeSize = ""
    }

    fun clearShoes() {
        shoes.clear()
    }

    val shoeObservable = ShoeObservable()

    inner class ShoeObservable : BaseObservable() {
        var shoeSize: String = ""

        @Bindable
        fun getNewSize(): String {
            return shoeSize
        }

        @Bindable
        fun setNewSize(size: String) {
            if (shoeSize != size) {
                shoeSize = size

                notifyPropertyChanged(BR.newSize)
            }
        }

    }
}
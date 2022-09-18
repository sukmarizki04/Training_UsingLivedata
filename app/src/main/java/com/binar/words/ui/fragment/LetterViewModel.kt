package com.binar.words.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LetterViewModel : ViewModel() {

    private val _letter: MutableLiveData<String> = MutableLiveData()
    val letter: LiveData<String> get() = _letter

    fun getLetter(letter: String) {
        _letter.value = letter
    }
}
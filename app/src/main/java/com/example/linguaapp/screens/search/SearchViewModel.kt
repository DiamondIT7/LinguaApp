package com.example.linguaapp.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.linguaapp.data.repository.Repository
import com.example.linguaapp.model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val repository = Repository()

    // LiveData for word details
    private val _wordDetails = MutableLiveData<ApiResponse>()
    val wordDetails: LiveData<ApiResponse> get() = _wordDetails

    // LiveData for API loading status
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // LiveData for API error handling
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun searchWord(word: String) {
        _isLoading.value = true
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getWordInfo(word)
                if (response.isSuccessful) {
                    _wordDetails.postValue(response.body())
                } else {
                    _error.postValue("Failed to fetch word details")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue("Network error: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
package com.bsuesi.testforwork.ui

import androidx.lifecycle.*
import com.bsuesi.testforwork.data.model.Article
import com.bsuesi.testforwork.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val query = MutableLiveData<String?>(null)
    fun setQuery(query: String?) {
        this.query.postValue(query)
    }

    private val _myResponse = MutableLiveData<List<Article>>()
    val myResponse: LiveData<List<Article>> = Transformations.switchMap(query) { query ->
        Transformations.map(_myResponse) { articles ->
            if (query.isNullOrBlank())
                articles
            else
                articles.filter {
                    it.title.contains(query, true)
                }
        }

    }
    init {
        getVoice()
    }
    fun getVoice() {
        viewModelScope.launch {
            val response = repository.getNews()
            _myResponse.postValue(response.articles)
        }
    }
}
package com.example.phnapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phnapplication.models.Data
import com.example.phnapplication.models.Posts
import com.example.phnapplication.models.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    val _users = MutableLiveData<ArrayList<Users>>()
    val users: LiveData<ArrayList<Users>>
        get() = _users

    val _posts = MutableLiveData<ArrayList<Posts>>()
    val posts: LiveData<ArrayList<Posts>>
        get() = _posts

    fun getAllUsers(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                PhnApi.phnService.getAllUsers()
            }.apply {
                _users.postValue(this)
            }
        }
    }

    fun getAllPosts(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                PhnApi.phnService.getAllPosts()
            }.apply {
                _posts.postValue(this)
            }
        }
    }

    fun filteredData(user: Users): ArrayList<Data>{
        val dataList = ArrayList<Data>()
        _posts.value?.filter {_data -> _data.userId == user.id}?.forEach {
            dataList.add(Data(user.name, user.company.name, it.title, it.body))
        }
        return dataList
    }
}
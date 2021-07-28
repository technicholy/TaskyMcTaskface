package com.example.taskymctaskface.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskymctaskface.data.local.model.Counter

class MainActivityViewModel : ViewModel() {
    private var counter = 0

    private var _counterList = MutableLiveData<List<Counter>>()
    val counterList : LiveData<List<Counter>>
        get() = _counterList

    init {
        val initList = buildFirstGroup()
        _counterList.postValue(initList)
    }

    private fun buildFirstGroup() : MutableList<Counter>{
        val buildList = mutableListOf<Counter>()
        for (i in 1 until 11){
            buildList.add(Counter(i))
            counter++
        }
        return buildList
    }
    fun addNewCounter(){
        val oldList = counterList.value?.toMutableList()
        counter++
        oldList?.add(Counter(counter))
        _counterList.postValue(oldList)
        }
}
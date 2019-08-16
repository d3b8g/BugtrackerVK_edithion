package net.d3b8g.bugtracker.HTTPS_VK

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main



abstract class BaseViewModel:ViewModel(){
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)
    private var isActive = true

    fun <P> doWork(doOnAsyncBlock: suspend CoroutineScope.() -> P){
        doCoroutineWork(doOnAsyncBlock, viewModelScope,IO)
    }

    fun <P> doWorkInMainThread(doOnAsyncBlock: suspend CoroutineScope.() -> P){
        doCoroutineWork(doOnAsyncBlock, viewModelScope,Main)
    }

    fun <P> doRepeatWork(delay:Long,doOnAsyncBlock: suspend CoroutineScope.() -> P){
        isActive = true
        viewModelScope.launch {
            while (this@BaseViewModel.isActive){
                withContext(IO){
                    doOnAsyncBlock.invoke(this)
                }
                if(this@BaseViewModel.isActive){
                    delay(delay)
                }
            }
        }
    }

    fun stopRepeatWork(){
        isActive = false
    }

    override fun onCleared() {
        super.onCleared()
        isActive = false
        viewModelJob.cancel()
    }

    private inline fun <P> doCoroutineWork(
        crossinline OnAsyncBlock: suspend CoroutineScope.() -> P,
        viewModelScope: CoroutineScope,
        context: CoroutineDispatcher
    ){
        viewModelScope.launch {
            withContext(context){
                OnAsyncBlock.invoke(this)
            }
        }
    }
}

class PostViewModel:BaseViewModel(){



    val lengthOfPostsList = MutableLiveData<String>()

    fun getListOfPosts(){
        doWork{
            val result = GetListOfPostsUseCan()
                .doWork(GetListOfPostsUseCan.Params())
            Log.e("RRR","${result.posts}")
            lengthOfPostsList.postValue(result.posts?.size.toString())
        }
    }
}











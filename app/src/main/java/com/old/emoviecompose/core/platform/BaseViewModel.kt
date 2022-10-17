package com.old.emoviecompose.core.platform

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.old.domain.model.Failure


abstract class BaseViewModel<in Event, State>  : ViewModel() {

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
    }

    private val _screenState: MutableLiveData<State?> = MutableLiveData()
    val screenState: MutableLiveData<State?>
        get() = _screenState

    fun postEvent(event: Event) {
        manageEvent(event)
    }

    protected abstract fun manageEvent(event: Event)
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))




fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer(body))

data class States<out T>(private val content:T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled():T?= if(hasBeenHandled){
        null
    }else{
        hasBeenHandled = true
        content
    }
}
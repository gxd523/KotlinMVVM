package com.gxd523.mvvm.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gxd523.mvvm.model.ModelDelegate
import com.gxd523.mvvm.model.impl.DatabaseModel
import com.gxd523.mvvm.model.impl.NetworkModel
import com.gxd523.mvvm.model.impl.SpModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected val networkModel: NetworkModel by ModelDelegate
    protected val databaseModel: DatabaseModel by ModelDelegate
    protected val spModel: SpModel by ModelDelegate
    protected val spModel2: SpModel by ModelDelegate
}

fun BaseViewModel.launch(
    block: suspend CoroutineScope.() -> Unit,
    onError: (e: Throwable) -> Unit = {},
    onComplete: () -> Unit = {},
) {
    viewModelScope.launch(CoroutineExceptionHandler { _, e -> onError(e) }) {
        try {
            block.invoke(this)
        } finally {
            onComplete()
        }
    }
}
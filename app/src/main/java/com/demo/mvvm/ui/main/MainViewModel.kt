package com.demo.mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import com.demo.mvvm.base.BaseViewModel
import com.demo.mvvm.base.launch
import com.demo.mvvm.bean.LoadState
import kotlinx.coroutines.async

class MainViewModel : BaseViewModel() {
    val imageList = MutableLiveData<List<String>>()
    val loadState = MutableLiveData<LoadState>()

    private var flag = false

    fun getImageList() {
        launch(
            {
                loadState.value = LoadState.Loading()
                val imageDeferred = async { networkModel.requestImageUrl(0, flag) }
                val imageDeferred1 = async { networkModel.requestImageUrl(1, flag) }
                val imageDeferred2 = async { networkModel.requestImageUrl(2, flag) }

                imageList.value = listOf(imageDeferred.await(), imageDeferred1.await(), imageDeferred2.await()).map {
                    it.imgUrl
                }

                loadState.value = LoadState.Success()
            },
            {
                loadState.value = LoadState.Fail(it.message ?: "加载失败")
            },
            {
                flag = !flag
            }
        )
    }
}
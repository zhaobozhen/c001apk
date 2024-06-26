package com.example.c001apk.ui.appupdate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c001apk.logic.repository.NetworkRepo
import com.example.c001apk.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateListViewModel @Inject constructor(
    private val networkRepo: NetworkRepo
): ViewModel() {

    var appName: String? = null
    var isInit: Boolean = true
    var versionName: String? = null
    var versionCode: String? = null
    var packageName: String? = null
    private var appId: String? = null
    val doNext = MutableLiveData<Event<Boolean>>()
    var url: String? = null

    fun onGetDownloadLink() {
        if (url.isNullOrEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                networkRepo.getAppDownloadLink(packageName.toString(), appId.toString(), versionCode.toString())
                    .collect { result ->
                        val link = result.getOrNull()
                        if (link != null) {
                            url = link
                            doNext.postValue(Event(true))
                        } else {
                            result.exceptionOrNull()?.printStackTrace()
                        }
                    }
            }
        } else
            doNext.postValue(Event(true))
    }

}
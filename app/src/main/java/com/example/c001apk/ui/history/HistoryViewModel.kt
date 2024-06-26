package com.example.c001apk.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.c001apk.logic.model.FeedEntity
import com.example.c001apk.logic.repository.BlackListRepo
import com.example.c001apk.logic.repository.HistoryFavoriteRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = HistoryViewModel.Factory::class)
class HistoryViewModel @AssistedInject constructor(
    @Assisted val type: String,
    private val blackListRepo: BlackListRepo,
    private val historyFavoriteRepo: HistoryFavoriteRepo,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(type: String): HistoryViewModel
    }

    val browseLiveData: LiveData<List<FeedEntity>> =
        if (type == "browse") {
            historyFavoriteRepo.loadAllHistoryListLive()
        } else {
            historyFavoriteRepo.loadAllFavoriteListLive()
        }


    fun saveUid(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            blackListRepo.saveUid(uid)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            when (type) {
                "browse" -> historyFavoriteRepo.deleteAllHistory()
                "favorite" -> historyFavoriteRepo.deleteAllFavorite()
                else -> {}
            }
        }
    }

    fun delete(fid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (type) {
                "browse" -> historyFavoriteRepo.deleteHistory(fid)
                "favorite" -> historyFavoriteRepo.deleteFavorite(fid)
                else -> {}
            }
        }
    }

    fun saveHistory(
        id: String,
        uid: String,
        username: String,
        userAvatar: String,
        deviceTitle: String,
        message: String,
        dateline: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            historyFavoriteRepo.saveHistory(
                id,
                uid,
                username,
                userAvatar,
                deviceTitle,
                message,
                dateline,
            )
        }
    }

}

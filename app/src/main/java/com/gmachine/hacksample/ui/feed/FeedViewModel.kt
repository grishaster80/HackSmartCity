package com.gmachine.hacksample.ui.feed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmachine.hacksample.data.CompetitionsResponse
import com.gmachine.hacksample.network.CompetitionsService
import com.gmachine.hacksample.repository.CompetitionsRepository
import com.gmachine.hacksample.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(competitionsRepository: CompetitionsRepository) :
    ViewModel() {

    val backData: MutableLiveData<ApiResult<CompetitionsResponse>> = MutableLiveData()


    init {
        viewModelScope.launch {
            competitionsRepository.getCompetitions().catch {
                Log.e("@@@", it.toString())
                backData.value = ApiResult.Error(it.toString())
            }.collect {
                backData.value = ApiResult.Success(it)
                Log.e("@@@", it.toString())
            }
        }
    }
}
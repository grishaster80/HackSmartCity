package com.gmachine.hacksample.ui.feed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmachine.hacksample.data.BackNews
import com.gmachine.hacksample.data.FeedNewsResponse
import com.gmachine.hacksample.repository.MainRepository
import com.gmachine.hacksample.ui.listOfMatchResult
import com.gmachine.hacksample.ui.listOfStories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FeedViewModel @Inject constructor(val mainRepository: MainRepository) :
    ViewModel() {

    val feedModel: MutableLiveData<FeedModel> = MutableLiveData()
    init {
        getNews()
    }
    private fun getNews() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.getNews().catch {
                    Log.e("@@@", it.toString())
                }.collect {
                    val myModel = createFeedModel(it)
                    feedModel.postValue(myModel)
                }
            }
        }
    }

    private fun createFeedModel(feedNewsResponse: FeedNewsResponse): FeedModel {
        return FeedModel(listOfStories(), listOfMatchResult(), myNews(feedNewsResponse.feedNews))
    }

    private fun myNews(feedNews: List<BackNews>): List<NewsInfo> {
        val myNewsInfo = mutableListOf<NewsInfo>()
        feedNews.forEach {
            myNewsInfo.add(
                NewsInfo(
                    title = it.title,
                    subTitle = it.subTitle,
                    dateString = it.date,
                    imageUrl = it.image,
                    newsType = getType(it.newsType)
                )
            )
        }
        return myNewsInfo
    }

    private fun getType(type: String): NewsType{
        return if (type == "DEFAULT") {
            NewsType.DEFAULT_NEWS
        } else {
            NewsType.LIVE_NEWS
        }
    }

}
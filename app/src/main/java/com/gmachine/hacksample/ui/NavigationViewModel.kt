package com.gmachine.hacksample.ui

import androidx.lifecycle.ViewModel
import com.gmachine.hacksample.storage.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val storage: Storage
) : ViewModel() {
    fun isNeedToShowOnboarding(): Boolean {
        return storage.getIsNeedToShowOnboarding()
    }

    fun onBoardingShown() {
        storage.setIsNeedToShowOnboarding(false)
    }
}
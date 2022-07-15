package com.gmachine.hacksample.storage

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Storage @Inject constructor(@ApplicationContext context: Context) {
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getAuthToken(): String {
        return prefs.getString(AUTH_TOKEN_KEY, "") ?: ""
    }

    fun setAuthToken(authToken: String) {
        prefs.edit().putString(AUTH_TOKEN_KEY, authToken).apply()
    }

    fun getIsNeedToShowOnboarding(): Boolean {
        return prefs.getBoolean(ONBOARDING_KEY, true)
    }

    fun setIsNeedToShowOnboarding(value: Boolean) {
        prefs.edit().putBoolean(ONBOARDING_KEY, value).apply()
    }

    companion object {
        const val AUTH_TOKEN_KEY = "AUTH_TOKEN_KEY"
        const val ONBOARDING_KEY = "ONBOARDING_KEY"
    }
}
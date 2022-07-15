package com.gmachine.hacksample.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmachine.hacksample.data.*
import com.gmachine.hacksample.repository.MainRepository
import com.gmachine.hacksample.storage.Storage
import com.gmachine.hacksample.utils.ApiResult
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.lang.Thread.sleep
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val storage: Storage, private val mainRepository: MainRepository) : ViewModel() {

    val userSignUpResponse: MutableLiveData<ApiResult<UserSignUpResponse>> = MutableLiveData()

    val userSignInResponse: MutableLiveData<Boolean> = MutableLiveData(false)

    val profileModel: MutableLiveData<ProfileModel> = MutableLiveData()

    fun isUserAuthorized(): Boolean {
        return !storage.getAuthToken().isNullOrEmpty()
    }

    fun signUp(signUpInfo: SignUpInfo) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.signUp(signUpInfo).catch {
                    Log.e("@@@", "error is $it")
                }.collect() {
                    Log.e("@@@","success")
                }
            }

        }
    }

    fun signIn(signInInfo: SignInInfo) {
        viewModelScope.launch {
            try {
                val client = OkHttpClient().newBuilder()
                    .build()
                val mediaType: MediaType? = "application/x-www-form-urlencoded".toMediaTypeOrNull()
                val body: RequestBody = RequestBody.create(
                    mediaType,
                    "client_id=test-client&grant_type=password&username=${signInInfo.username}&password=${signInInfo.password}"
                )
                val request: Request = Request.Builder()
                    .url("http://35.173.193.253/auth/realms/Test/protocol/openid-connect/token")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build()
                withContext(Dispatchers.IO) {
                    val response: Response = client.newCall(request).execute()
                    Log.e("@@@","response is $response")
                    val myBody = response.body?.string()
                    Log.e("@@@","response body is ${myBody}")
                    val signInResponse: SignInResponse = Gson().fromJson(myBody, SignInResponse::class.java)
                    Log.e("@@@","signInResponse is ${signInResponse}")
                    storage.setAuthToken(signInResponse.access_token)
                    userSignInResponse.postValue(true)
                    sleep(2000)
                    fetchUserInfo()
                }

            } catch(e: java.lang.Exception) {
                Log.e("@@@","$e")
            }
        }
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.getUser(storage.getAuthToken()).catch {
                    Log.e("@@@",it.toString())
                }.collect {
                    profileModel.postValue(ProfileModel(fullName = it.firstName, login = it.username))
                }
            }
        }
    }
}
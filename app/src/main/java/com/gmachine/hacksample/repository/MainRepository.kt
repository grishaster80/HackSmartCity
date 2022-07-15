package com.gmachine.hacksample.repository

import com.gmachine.hacksample.data.*
import com.gmachine.hacksample.network.MainService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainService: MainService
) {
    suspend fun getCompetitions(): Flow<CompetitionsResponse> {
        return flow<CompetitionsResponse> {
            emit(mainService.getFoodCategories())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun signUp(signUpInfo: SignUpInfo): Flow<UserSignUpResponse> {
        return flow<UserSignUpResponse> {
            emit(mainService.signUp(signUpInfo))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun signIn(signInInfo: SignInInfo): Flow<SignInResponse> {
        return flow<SignInResponse> {
            emit(mainService.signIn(signInInfo))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUser(token: String): Flow<UserResponse> {
        return flow<UserResponse> {
            emit(mainService.getUser("Bearer $token"))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNews(): Flow<FeedNewsResponse> {
        return flow<FeedNewsResponse> {
            emit(mainService.getNews())
        }.flowOn(Dispatchers.IO)
    }
}
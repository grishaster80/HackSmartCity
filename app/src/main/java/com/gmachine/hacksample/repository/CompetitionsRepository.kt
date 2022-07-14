package com.gmachine.hacksample.repository

import com.gmachine.hacksample.data.CompetitionsResponse
import com.gmachine.hacksample.network.CompetitionsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CompetitionsRepository @Inject constructor(
    val competitionsService: CompetitionsService
) {
    suspend fun getCompetitions(): Flow<CompetitionsResponse> {
        return flow<CompetitionsResponse> {
            emit(competitionsService.getFoodCategories())
        }.flowOn(Dispatchers.IO)
    }
}
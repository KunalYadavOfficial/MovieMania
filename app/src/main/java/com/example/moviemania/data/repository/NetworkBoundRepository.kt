package com.example.moviemania.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.moviemania.model.MovieData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

abstract class NetworkBoundRepository<REQUEST,RESULT> {


    fun asFlow() = flow<Resource<RESULT>>{

        emit(Resource.Success(fetchFromLocal().first()))

        val apiResponse = fetchRemoteData()

        val remoteMovieData = apiResponse.body()

        if(apiResponse.isSuccessful && remoteMovieData!=null)
        {
            saveRemoteData(apiResponse)
        }
        else
        {
            emit(Resource.Failed(apiResponse.message()))
        }

        emitAll(
            fetchFromLocal()
                .map {
                    Resource.Success(it)
                }
        )

    }.catch {e->
        e.printStackTrace()
        /*emit(
            //Resource.Failed("Network Error!!.Request cannot be completed")
        Resource.Failed(e.toString())
        )*/
    }


    @WorkerThread
    protected abstract suspend fun fetchRemoteData() : Response<REQUEST>

    @WorkerThread
    protected abstract suspend fun saveRemoteData(response : Response<REQUEST>)

    @MainThread
    protected abstract fun fetchFromLocal() : Flow<RESULT>


    //need to check why it is used to
}
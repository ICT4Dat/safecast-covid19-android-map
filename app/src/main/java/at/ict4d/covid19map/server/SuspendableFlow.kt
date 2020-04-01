package at.ict4d.covid19map.server

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import timber.log.Timber
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
fun <ResultType, RemoteResultType> resultFlow(
    databaseQuery: () -> Flow<ResultType>,
    networkCall: suspend () -> Response<RemoteResultType>,
    saveCallResult: suspend (RemoteResultType) -> Unit,
    emitFutureDatabaseUpdates: Boolean = true,
    shouldFetch: (data: ResultType) -> Boolean = { true }
): Flow<Resource<ResultType>> = flow {

    val dbSource = databaseQuery.invoke()
    val dbResult = dbSource.first()

    if (shouldFetch.invoke(dbResult)) {

        emit(Resource.loading(dbResult))

        try {
            val response = networkCall.invoke()

            val resourceFlow = when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    saveCallResult(apiResponse.body)
                    dbSource.map { Resource.success(it, apiResponse.responseCode) }
                }
                is ApiEmptyResponse -> {
                    dbSource.map { Resource.success(it, apiResponse.responseCode) }
                }
                is ApiErrorResponse -> {
                    dbSource.map {
                        Resource.error(
                            apiResponse.throwable,
                            it,
                            apiResponse.responseCode
                        )
                    }
                }
            }

            if (emitFutureDatabaseUpdates) {
                emitAll(resourceFlow)
            } else {
                emit(resourceFlow.first())
            }
        } catch (e: UnknownHostException) {

            Timber.w(e, "UnknownHostException in resultFlow")

            if (emitFutureDatabaseUpdates) {
                emitAll(dbSource.map {
                    Resource.error(e, it, null)
                })
            } else {
                emit(Resource.error(e, dbSource.first(), null))
            }
        }
    } else {
        if (emitFutureDatabaseUpdates) {
            emitAll(dbSource.map { Resource.success(it, null) })
        } else {
            emit(Resource.success(dbResult, null))
        }
    }
}

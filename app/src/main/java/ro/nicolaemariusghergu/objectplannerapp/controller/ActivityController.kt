package ro.nicolaemariusghergu.objectplannerapp.controller

import com.google.android.gms.common.api.Response
import com.google.android.gms.common.api.Result
import retrofit2.http.GET

interface ActivityController {

    @GET("/activities")
    suspend fun getQuotes() : Response<Result>
}
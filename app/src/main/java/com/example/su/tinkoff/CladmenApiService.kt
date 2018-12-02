package com.example.su.tinkoff

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CladmenApiService{

    @GET("sync")
    fun sync(@Query("sid") sid: String,
             @Query("count") count: Int,
             @Query("longitude") longitude: Float,
             @Query("latitude") latitude: Float): Observable<CladmenModel.Result>

    @GET("my")
    fun my(@Query("sid") sid: String) : Observable<Array<MyModel.MyResult>>

    companion object {
        fun create(): CladmenApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://vk.lisenkosoft.ru/api/cladmen/")
                    .build()

            return retrofit.create(CladmenApiService::class.java)

        }
    }
}
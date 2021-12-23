package com.octaedges.advanceandroid.model

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api: CountriesApi

    init {
        api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(CountriesApi::class.java)

    }

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}
package com.octaedges.advanceandroid.model

import com.octaedges.advanceandroid.DI.DaggerApiComponet
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerApiComponet.create().inject(this)
    }

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}
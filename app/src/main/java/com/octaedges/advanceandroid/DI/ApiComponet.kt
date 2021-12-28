package com.octaedges.advanceandroid.DI

import com.octaedges.advanceandroid.model.CountriesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponet {
    fun inject(service: CountriesService)
}
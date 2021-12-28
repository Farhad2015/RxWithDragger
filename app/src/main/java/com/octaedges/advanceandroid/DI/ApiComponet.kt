package com.octaedges.advanceandroid.DI

import com.octaedges.advanceandroid.model.CountriesService
import com.octaedges.advanceandroid.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponet {
    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)
}
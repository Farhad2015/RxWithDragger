package com.octaedges.advanceandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.octaedges.advanceandroid.model.CountriesService
import com.octaedges.advanceandroid.model.Country
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class ListViewModel : ViewModel() {
    private val countriesService = CountriesService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(
                countriesService.getCountries()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                            override fun onSuccess(value: List<Country>?) {
                                countries.value = value
                                countryLoadError.value = false
                                loading.value = false
                            }

                            override fun onError(e: Throwable?) {
                                countryLoadError.value = true
                                loading.value = false
                            }

                        })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
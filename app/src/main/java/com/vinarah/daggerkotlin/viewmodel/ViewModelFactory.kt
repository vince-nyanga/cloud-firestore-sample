package com.vinarah.daggerkotlin.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import javax.inject.Inject
import javax.inject.Provider

/**
 * @author Vincent
 * *
 * @since 2017/10/03
 */
// We use @JvmSuppressWildcards to prevent the compiler from generating wildcards
class ViewModelFactory @Inject constructor(val creators: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class " + modelClass)
        }
        try {
            @Suppress("UNCHECKED_CAST") return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}

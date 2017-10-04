package com.vinarah.daggerkotlin.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.vinarah.daggerkotlin.repository.FirestoreRepository
import com.vinarah.daggerkotlin.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Vincent
 * @since 2017/10/03
 */
@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {

    @Provides
    fun providesSharedPreferences(app: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }

    @Provides
    fun providersRepository(): Repository{
        return FirestoreRepository()
    }
}
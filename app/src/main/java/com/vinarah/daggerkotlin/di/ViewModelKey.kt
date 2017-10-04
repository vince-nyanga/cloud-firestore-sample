package com.vinarah.daggerkotlin.di

import android.arch.lifecycle.ViewModel
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import dagger.MapKey
import kotlin.reflect.KClass

/**
 * @author Vincent
 * *
 * @since 2017/10/03
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RetentionPolicy.RUNTIME)
@MapKey annotation class ViewModelKey(val value: KClass<out ViewModel>)

package com.vinarah.daggerkotlin.vo;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Vincent
 * @since 2017/10/04
 */

public class Resource<T> {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LOADING,SUCCESS,ERROR,EMPTY})
    public @interface Status{}
    public static final int LOADING = 1;
    public static final int SUCCESS = 2;
    public static final int ERROR = 3;
    public static final int EMPTY = 4;

    public final T value;
    public final String message;
    public final @Status int status;

    private Resource(T value, @Status int status, String message) {
        this.value = value;
        this.status = status;
        this.message = message;
    }

    public static <T> Resource<T> success(T value) {
        return new Resource<>(value, SUCCESS, null);
    }

    public static <T> Resource<T> error(String message) {
        return  new Resource<>(null, ERROR, message);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(null, LOADING, null);
    }

    public static <T> Resource<T> empty(){
        return new Resource<>(null,EMPTY,null);
    }

}

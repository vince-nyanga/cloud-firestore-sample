package com.vinarah.daggerkotlin.model

/**
 * @author Vincent
 * @since 2017/10/04
 */
data class Todo (var name: String, var done: Boolean = false) {
    constructor():this("",false)
}
package io.skerna.i18nexceptions

import org.jetbrains.annotations.PropertyKey

class ErrorCode(keyProperty:String):CharSequence by keyProperty

fun String.asErrorCode():ErrorCode{
    return ErrorCode(this)
}
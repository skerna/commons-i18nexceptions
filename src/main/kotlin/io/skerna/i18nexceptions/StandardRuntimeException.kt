/*
 * Copyright (c)  2019  SKERNA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.skerna.i18nexceptions


import org.jetbrains.annotations.PropertyKey
import java.lang.RuntimeException
import java.util.ArrayList

/**
 * Created by ronald on 02/02/18.
 */
abstract class StandardRuntimeException  : RuntimeException, I18NException {
    private val errorCode:ErrorCode;
    private val additionalCodes: MutableList<ErrorCode> by lazy { mutableListOf<ErrorCode>() }
    private val additionalMessages: MutableList<String> by lazy { mutableListOf<String>() }
    private val _render: Render by lazy { initialize_render() }

    constructor( errorCode: String) : super() {
        this.errorCode = ErrorCode(errorCode)
    }


    constructor( errorCode: ErrorCode) : super() {
        this.errorCode = errorCode
    }

    constructor( errorCode: ErrorCode,message: String?) : super(message) {
        this.errorCode = errorCode
    }

    constructor( errorCode: ErrorCode,message: String?, cause: Throwable?) : super(message, cause) {
        this.errorCode = errorCode
    }

    constructor( errorCode: ErrorCode,cause: Throwable?) : super(cause) {
        this.errorCode = errorCode
    }

    constructor( errorCode: ErrorCode,message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace) {
        this.errorCode = errorCode
    }


    open fun appendMessage(message: String) = apply {
        additionalMessages.add(message)
    }
    open fun appendErrorCode(errorCode: ErrorCode) = apply{
        additionalCodes.add(errorCode)
    }

    open fun appendErrorCode(errorCode: String) = apply{
        additionalCodes.add(ErrorCode(errorCode))
    }

    override fun getErrorCode(): ErrorCode  = errorCode

    override fun getAdditionalErrorCodes(): List<ErrorCode>  = getAdditionalErrorCodes()

    override fun hasAdditionalErrorCodes(): Boolean = this::additionalCodes.isLazyInitialized

    @Synchronized
    override fun fillInStackTrace(): Throwable {
        return this
    }

    override fun toString(): String {

        return "\n[" + i18N.CODE + "]=" + errorCode +
                "\n[" + i18N.MESSAGE + "]=" + message +
                "\n[" + i18N.DETAILS + "]=" + additionalMessages.toString()
    }


    override val message: String?
        get() {
            var message = ""
            val result = _render.render(this)
            if (result.isPresent) {
                val _render = result.get()
                message = _render.mensaje
            }
            return message
        }


    private fun initialize_render(): Render {
        return getRender()
    }

}
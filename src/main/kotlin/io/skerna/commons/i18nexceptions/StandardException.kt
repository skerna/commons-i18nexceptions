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

package io.skerna.commons.i18nexceptions


/**
 * Created by ronald on 02/02/18.
 */

abstract class StandardException  : RuntimeException, I18NException {
    private val String:String;
    private val additionalCodes: MutableList<String> by lazy { mutableListOf<String>() }
    private val additionalMessages: MutableList<String> by lazy { mutableListOf<String>() }
    private val _render: Render by lazy { initialize_render() }

    constructor( String: String) : super() {
        this.String = String
    }

    constructor( String: String,message: String?) : super(message) {
        this.String = String
    }

    constructor( String: String,message: String?, cause: Throwable?) : super(message, cause) {
        this.String = String
    }

    constructor( String: String,cause: Throwable?) : super(cause) {
        this.String = String
    }

    constructor( String: String,message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace) {
        this.String = String
    }


    fun appendMessage(message: String) = apply {
        additionalMessages.add(message)
    }

    override fun getErrorCode(): String  = String

    override fun getAdditionalErrorCodes(): List<String>  = getAdditionalErrorCodes()

    override fun hasAdditionalErrorCodes(): Boolean = this::additionalCodes.isLazyInitialized

    @Synchronized
    override fun fillInStackTrace(): Throwable {
        return this
    }

    override fun toString(): String {

        return "\n[" + i18N.CODE + "]=" + String +
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

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

import java.util.Locale
import java.util.Optional

open class ExceptionRender(private val catalog: MessageCatalog) : Render {

    override fun render(i18NExceptionBase: I18NException, locale: Locale): Optional<RenderResult> {
        val code = i18NExceptionBase.getErrorCode()
        val additionalCodes = i18NExceptionBase.getAdditionalErrorCodes()
        val messageTranslated = catalog.getLocalizedMessage(code, locale)


        val messageTranslatedList = additionalCodes.associateWith(catalog::getLocalizedMessage)

        var renderResult = RenderResult(code, messageTranslated,messageTranslatedList)
        return Optional.ofNullable(renderResult)
    }

    override fun render(i18NExceptionBase: I18NException): Optional<RenderResult> {
        return render(i18NExceptionBase, Locale.getDefault())
    }
}

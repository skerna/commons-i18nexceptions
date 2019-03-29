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


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.Locale

class ResourceMessageCatalogTest {

    private var message: MessageCatalog = TestCatalog

    @Test
    @Throws(ExceptionCode::class)
    fun testRuntimeException() {
        try {
            throw ExceptionCode("CODIGO_ITEM_NOTFOUND")
                    .appendMessage("Algo de context en tu idioma ...")


        } catch (ex: Exception) {
            ExceptionRender(message!!).render(ex as I18NException)
            assertEquals(ex.javaClass, ExceptionCode::class.java)
        }

    }

    @Test
    @Throws(ExceptionCode::class)
    fun testRuntimeUnknowCode() {
        try {
            val a = ExceptionUnknowCode()
            ExceptionRender(message!!).render(a)
        } catch (ex: Exception) {
            assertEquals(ex.javaClass, I18NInternalException::class.java)
        }

    }


    @Test
    fun getLocalizedMessage() {
        assertEquals("Ingles Item not found", message!!.getLocalizedMessage("CODIGO_TEST", Locale.ENGLISH))
        assertEquals("No encontrado", message!!.getLocalizedMessage("CODIGO_TEST", Locale("es", "EC")))
    }

    @Test
    fun hasCode() {
        val result = message!!.hasCode("CODIGO_ITEM_NOTFOUND")
        assertEquals(result, true)
    }

}
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

import java.util.Locale

interface MessageCatalog {

    /**
     * El cliente debe retorna el
     * path de ResourceBundle para el paquete
     * en el que esta trabajando.
     * Ejm io.r2b.pack.messages.namebundle
     * en donde el directorio corresponde a resource files path, del proyecto
     * @return
     */
    val bundleName: String

    /**
     * Obtiene un mesage i18n desde resources
     * @param code
     * @return
     */
    fun getLocalizedMessage(code: ErrorCode, locale: Locale): String


    /**
     * Obtiene un mesage i18n desde resources
     * @param code
     * @return
     */
    fun getLocalizedMessage(code: ErrorCode): String


    /**
     * Verifica si el código esta disponible en la biblioteca de mensajes
     * @param code
     * @return
     */
    fun hasCode(code: ErrorCode): Boolean

    /**
     * Verifica si el catalogo tiene la clave
     * @param code
     * @param locale
     * @return
     */
    fun hasCode(code: ErrorCode, locale: Locale): Boolean


}

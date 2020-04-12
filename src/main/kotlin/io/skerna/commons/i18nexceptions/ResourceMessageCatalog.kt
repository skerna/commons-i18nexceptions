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

import java.util.*

import io.skerna.commons.i18nexceptions.Utils.isNullOrEmpty

open class ResourceMessageCatalog : MessageCatalog {
    override val bundleName: String
    private var classLoader:ClassLoader?=null
    /**
     * Defult contructor, espera encontrar en el path un resource bundle con el nombre
     * messsages
     */
    constructor(bundleName: String) {
        val _nameBundle = if (isNullOrEmpty(bundleName)) MESSAGE_RESOURCE else bundleName
        this.bundleName = _nameBundle
    }

    /**
     * Defult contructor, espera encontrar en el path un resource bundle con el nombre
     * messsages
     */
    constructor(bundleName: String, classLoader: ClassLoader):this(bundleName) {
        this.classLoader = classLoader
    }


    override fun getLocalizedMessage(code: String, locale: Locale): String {
        val bundle = loadBundle(locale,classLoader)
        var message: String = ""
        if (bundle.isPresent) {
            try {
                message = bundle.get().getString(code.toString())
            } catch (e: Exception) {

            }

        }
        if (isNullOrEmpty(message)) {
            for (key in bundle.get().keys) {
                println(key)
            }
            throw I18NInternalException(String.format("Code [%s] is not part of catalog $bundleName in Catalog ", code))
        }

        return message

    }


    override fun getLocalizedMessage(code: String): String {
        return getLocalizedMessage(code, Locale.getDefault())
    }

    /**
     * @param locale NULLEABLE
     * @param classLoader
     * @return
     */
    private fun loadBundle(locale: Locale,loader:ClassLoader?=null): Optional<ResourceBundle> {
        val keyBundle = bundleName + "_" + locale.country
        if (!references.containsKey(keyBundle)) {
            var bundle: ResourceBundle?
            try {
                if(loader == null){
                    bundle = ResourceBundle.getBundle(bundleName, locale)
                }else{
                    bundle = ResourceBundle.getBundle(bundleName, locale,loader)
                }
                references[keyBundle] = bundle
            } catch (ex: Exception) {
                throw I18NInternalException(String.format("bundle %s not found", bundleName))
            }

        }
        return Optional.ofNullable(references[keyBundle])
    }

    override fun hasCode(code: String): Boolean {
        return hasCode(code, Locale.getDefault())
    }

    override fun hasCode(code: String, locale: Locale): Boolean {
        val bundle = loadBundle(locale,classLoader)
        return bundle.map { resourceBundle -> resourceBundle.containsKey(code.toString()) }.orElse(false)
    }

    companion object {
        // Cache resource bundles
        private val references = WeakHashMap<String, ResourceBundle>()
        val MESSAGE_RESOURCE = "messages"
    }

}

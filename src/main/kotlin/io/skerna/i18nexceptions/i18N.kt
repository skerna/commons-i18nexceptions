/*
 * Copyright 2018 R2B
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * NCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package io.skerna.i18nexceptions

import java.util.ResourceBundle

import io.skerna.i18nexceptions.Utils.isNullOrEmpty

@SuppressWarnings
internal object i18N {
    val BUNDLE_INTERNAL_NAME = "i18nexceptions"

    val CODE = dataInitializer(i18N.I18nKeys.CODE_KEY)
    val MESSAGE = dataInitializer(i18N.I18nKeys.MESSAGE_KEY)
    val DETAILS = dataInitializer(i18N.I18nKeys.DETAILS_KEY)
    val SPECIFIC = dataInitializer(i18N.I18nKeys.SPECIFIC_KEY)

    internal enum class I18nKeys private constructor(internal val key: String, internal val defaultValue: String) {
        CODE_KEY("CODE", "code"),
        MESSAGE_KEY("MESSAGE", "message"),
        DETAILS_KEY("DETAILS", "details"),
        SPECIFIC_KEY("SPECIFIC", "specific")

    }


    private fun dataInitializer(wichKey: I18nKeys): String {
        var bunlde: ResourceBundle? = null
        try {
            bunlde = ResourceBundle.getBundle(BUNDLE_INTERNAL_NAME)
        } catch (e: Exception) {
        }

        var name = when (wichKey) {
            i18N.I18nKeys.CODE_KEY -> {
                val _cod = if (bunlde != null) bunlde.getString(i18N.I18nKeys.CODE_KEY.key) else i18N.I18nKeys.CODE_KEY.defaultValue
                return if (isNullOrEmpty(_cod)) "Code" else _cod
            }
            i18N.I18nKeys.DETAILS_KEY -> {
                val _det = if (bunlde != null) bunlde.getString(i18N.I18nKeys.DETAILS_KEY.key) else i18N.I18nKeys.DETAILS_KEY.defaultValue
                return if (isNullOrEmpty(_det)) "Details" else _det
            }
            i18N.I18nKeys.MESSAGE_KEY -> {
                val _mess = if (bunlde != null) bunlde.getString(i18N.I18nKeys.MESSAGE_KEY.key) else i18N.I18nKeys.MESSAGE_KEY.defaultValue
                return if (isNullOrEmpty(_mess)) "Message" else _mess
            }
            i18N.I18nKeys.SPECIFIC_KEY -> {
                val _spec = if (bunlde != null) bunlde.getString(i18N.I18nKeys.SPECIFIC_KEY.key) else i18N.I18nKeys.SPECIFIC_KEY.defaultValue
                return if (isNullOrEmpty(_spec)) "Spec" else _spec
            }
            else ->  "Unknow"
        }
        return name
    }


}

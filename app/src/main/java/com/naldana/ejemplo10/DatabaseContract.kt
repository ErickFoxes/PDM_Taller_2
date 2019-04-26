package com.naldana.ejemplo10

import android.provider.BaseColumns

object DatabaseContract {

    object MonedaEntry : BaseColumns { // Se guardan los datos relevantes de la tabla, como su nombre y sus campos.

        const val TABLE_NAME = "moneda"

        // Se crea una constante por cada columna de la tabla.
        const val COLUMN_NAME = "name"
        const val COLUMN_COUNTRY = "country"
        const val COLUMN_VALUE = "value"
        const val COLUMN_VALUE_US = "value_us"
        const val COLUMN_YEAR = "year"
        const val COLUMN_REVIEW = "review"
        const val COLUMN_ISAVAILABLE = "isavailable"
        const val COLUMN_IMAGE = "image"

    }
}
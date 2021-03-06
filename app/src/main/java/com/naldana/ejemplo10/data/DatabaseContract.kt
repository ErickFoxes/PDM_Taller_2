package com.naldana.ejemplo10.data

import android.provider.BaseColumns

object DatabaseContract {
    object MonedaEntry : BaseColumns {
        const val TABLE_NAME = "moneda"

        const val COLUMN_NAME = "name"
        const val COLUMN_COUNTRY = "country"
        const val COLUMN_VALUE = "value"
        const val COLUMN_VALUE_US = "value_us"
        const val COLUMN_YEAR = "year"
        const val COLUMN_REVIEW = "review"
        const val COLUMN_ISAVAILABLE = "isAvailable"
        const val COLUMN_IMAGE = "image"
    }
}
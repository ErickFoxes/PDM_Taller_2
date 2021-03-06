package com.naldana.ejemplo10.models

//data class Moneda(val id: Int, val name: String, val fsttype: String, val sndtype: String, val weight: String, val height: String, val url:String, val sprite:String)
data class Moneda(
    val id: String,
    val name: String,
    val country: String,
    val value: Number,
    val value_us: Number,
    val year: Number,
    val review: String,
    val isAvailable: Boolean,
    val image: String
)
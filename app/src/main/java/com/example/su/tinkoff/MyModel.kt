package com.example.su.tinkoff

object MyModel{
    data class MyResult(
            var id: String,
            var phone: String?,
            var name: String?,
            var contract: String?,
            var latitude: Float,
            var longitude: Float,
            var address: String?,
            var manager: Int,
            var cladmen: Int,
            var dateTime: String,
            var opened: String,
            var closed: String?
    )
}
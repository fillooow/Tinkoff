package com.example.su.tinkoff

data class OrderInfo(
        var id: Long,
        var phone: String,
        var name: String,
        var contract: String,
        var latitude: Double,
        var longitude: Double,
        var address: String,
        var dateTime: String
)
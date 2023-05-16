package com.example.demo.adapters.dto

import java.time.Instant

data class ErrorMessageModel (
    var status: Int? = null,
    var message: String? = null,
    var timestamp: Instant? = null,
    var error: String? = null
)
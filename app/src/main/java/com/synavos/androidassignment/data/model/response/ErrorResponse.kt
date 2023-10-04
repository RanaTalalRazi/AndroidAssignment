package com.synavos.androidassignment.data.model.response

data class ErrorResponse(
    val debugMessage: String,
    val errorCode: String?,
    val error: String?,
    val message: String?,
    val status: String?,
    val subErrors: Any?,
    val timestamp: String?
)
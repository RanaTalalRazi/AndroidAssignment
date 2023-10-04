package com.synavos.androidassignment.data.model

import com.synavos.androidassignment.data.model.response.ErrorResponse


class RequestException(val code: Int, message: String, var errorObject: ErrorResponse? = null) :
    Throwable(message)

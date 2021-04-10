package com.zlrx.examples.genericservice.error

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class ServiceException(
    errorMessage: String,
    httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
) : ResponseStatusException(httpStatus, errorMessage, null) {
}
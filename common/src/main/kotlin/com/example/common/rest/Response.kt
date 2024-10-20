package com.example.common.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class Response<T>(
  data: T?,
  status: HttpStatus = HttpStatus.OK,
) : ResponseEntity<Any>(ResponseData(data), status) {

  class ResponseData<T>(val data: T?)
}

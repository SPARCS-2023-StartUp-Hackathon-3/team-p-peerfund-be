package kr.peerfund.controller

import kr.peerfund.dto.ResponseMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.client.HttpServerErrorException

@RestControllerAdvice
class ExceptionController {
    @ExceptionHandler(*[MethodArgumentNotValidException::class, WebExchangeBindException::class])
    fun handleMethodArgumentNotValidException(ex: Exception): ResponseEntity<Any> {
        val bindingResult: BindingResult? = when (ex) {
            is MethodArgumentNotValidException -> {
                ex.bindingResult
            }

            is WebExchangeBindException -> {
                ex.bindingResult
            }

            else -> null
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).run {
            bindingResult?.fieldErrors?.first()?.let { fieldError ->
                body(ResponseMessage(fieldError.defaultMessage))
            } ?: build()
        }
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(ex: UsernameNotFoundException): ResponseEntity<ResponseMessage> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage(ex.message))
    }

    @ExceptionHandler(HttpServerErrorException::class)
    fun handleHttpServerErrorException(ex: HttpServerErrorException): ResponseEntity<ResponseMessage> {
        return ResponseEntity.status(ex.statusCode).body(ResponseMessage(ex.statusText))
    }

    @ExceptionHandler(Exception::class)
    fun handleDefaultException(ex: Exception): ResponseEntity<ResponseMessage> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage(ex.message))
    }
}

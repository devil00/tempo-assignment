package com.example.demo.adapters.handlers

import com.example.demo.adapters.dto.ErrorMessageModel
import com.example.demo.common.exceptions.MemberNotFoundException
import com.example.demo.common.exceptions.MemberShipNotFoundException
import com.example.demo.common.exceptions.RoleNotFoundException
import org.postgresql.util.PSQLException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Clock
import javax.validation.ConstraintViolationException

@ControllerAdvice
class ExceptionControllerAdvice {
    @ExceptionHandler
    fun handleMemberNotFoundException(ex: MemberNotFoundException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleMemberShipNotFoundException(ex: MemberShipNotFoundException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleRoleNotFoundException(ex: RoleNotFoundException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.bindingResult.getFieldErrors().map { fe -> fe.defaultMessage }.toMutableList().joinToString(","),
                    Clock.systemDefaultZone().instant(),
            "Validator Error"
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handlePostgresqlException(ex: PSQLException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            Clock.systemDefaultZone().instant(),
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            Clock.systemDefaultZone().instant(),
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}
package com.inspirecoding.registrationflow_1

data class PasswordValidation (
    var isAtLeast8 : Boolean = false,
    var hasUppercase : Boolean = false,
    var hasNumber : Boolean = false,
    var hasSymbol : Boolean = false
)
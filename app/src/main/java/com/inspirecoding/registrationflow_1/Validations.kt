package com.inspirecoding.registrationflow_1

import android.util.Log
import java.util.regex.Matcher
import java.util.regex.Pattern

object Validations
{
    private var regexPattern: Pattern? = null
    private var regMatcher: Matcher? = null

    fun validateEmailAddress(emailAddress: String): Boolean
    {
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$")
        regMatcher = (regexPattern as Pattern).matcher(emailAddress)
        return (regMatcher as Matcher).matches()
    }
    fun validatePassword(password: String) : PasswordValidation
    {
        val passwordValidation = PasswordValidation (
            isAtLeast8 = false,
            hasUppercase = false,
            hasNumber = false,
            hasSymbol = false
        )

        passwordValidation.isAtLeast8 = password.length >= 8
        passwordValidation.hasUppercase = password.matches(Regex(pattern = "(.*[A-Z].*)"))
        passwordValidation.hasNumber = password.matches(Regex(pattern = "(.*[0-9].*)"))
        passwordValidation.hasSymbol = password.matches(Regex(pattern = "^(?=.*[_.()/*+-]).*\$"))
        Log.d("Validations", "$passwordValidation")
        Log.d("Validations", "$password")

        return passwordValidation
    }

    fun validatePhoneNumber(mobileNumber: String?): Boolean
    {
        regexPattern = Pattern.compile("^\\+[0-9]{2,3}+-[0-9]{10}$")
        regMatcher = (regexPattern as Pattern).matcher(mobileNumber)
        return (regMatcher as Matcher).matches()
    }
}
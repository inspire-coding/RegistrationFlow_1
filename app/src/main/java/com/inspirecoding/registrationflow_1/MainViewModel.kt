package com.inspirecoding.registrationflow_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel()
{
    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid: LiveData<Boolean> = _isEmailValid

    private val _isPasswordValid = MutableLiveData<PasswordValidation>()
    val isPasswordValid: LiveData<PasswordValidation> = _isPasswordValid

    fun validateEmailAddress(emailAddress: String)
    {
        _isEmailValid.value = Validations.validateEmailAddress(emailAddress)
    }

    fun validatePassword(password: String)
    {
        _isPasswordValid.value = Validations.validatePassword(password)
    }
}
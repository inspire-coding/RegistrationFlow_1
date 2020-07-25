package com.inspirecoding.registrationflow_1

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.DateTime
import java.util.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener
{
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupEmailObserver()
        setupPasswordObserver()
        disableMaterialCardViews()
        setupEmailTextChangedListener()
        setupPasswordTextChangedListener()

        start_materialCardView_birthDate.isEnabled = false
        tv_birthDate.setOnClickListener {
            showDatePickerDialog()
        }
    }

    fun showDatePickerDialog()
    {
        val datePickerDialog = DatePickerDialog (
            this,
            this,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = DateTime.now().millis - 568024668000
        datePickerDialog.datePicker.minDate = DateTime.now().millis - 3155692600000

        datePickerDialog.setOnShowListener {  _datePickerDialog ->
            /** Set the color of the action buttons, otherwise it will get it form the primary color! **/
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
        }
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int)
    {
        val date = ConverterFunctions.convertToDate(year, month, dayOfMonth)

        tv_birthDate.text = date.toLocaleString().substringBeforeLast(" ")

        start_materialCardView_birthDate.isEnabled = true
        tv_birthDate_next.setTextColor(ContextCompat.getColor(this, R.color.blue))
    }


    private fun setupEmailTextChangedListener()
    {
        tiet_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
                mainViewModel.validateEmailAddress(tiet_email.text.toString())
            }
        })
    }
    private fun setupPasswordTextChangedListener()
    {
        tiet_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
                mainViewModel.validatePassword(tiet_password.text.toString())
            }
        })
    }

    private fun disableMaterialCardViews()
    {
        start_materialCardView_email.isEnabled = false
        start_materialCardView_password.isEnabled = false
    }


    private fun setupEmailObserver()
    {
        mainViewModel.isEmailValid.observe(this, Observer {  _isEmailValid ->
            if (_isEmailValid)
            {
                iv_email_correct.setImageResource(R.drawable.ic_correct_circle_green_24)
                tv_email_error.setTextColor(ContextCompat.getColor(this, R.color.green))

                tv_email_next.setTextColor(ContextCompat.getColor(this, R.color.blue))

                start_materialCardView_email.isEnabled = true
            }
            else
            {
                iv_email_correct.setImageResource(R.drawable.ic_correct_circle_lightgray_24)
                tv_email_error.setTextColor(ContextCompat.getColor(this, R.color.light_gray))

                tv_email_next.setTextColor(ContextCompat.getColor(this, R.color.light_gray))

                start_materialCardView_email.isEnabled = false
            }
        })
    }
    private fun setupPasswordObserver()
    {
        mainViewModel.isPasswordValid.observe(this, Observer {  _isPasswordValid ->
            val (isAtLeast8, hasUppercase, hasNumber, hasSymbol) = _isPasswordValid
            Log.d("MainActivity", "$_isPasswordValid")
            if (isAtLeast8)
            {
                iv_password_isAtLeast8_correct.setImageResource(R.drawable.ic_correct_circle_green_24)
                tv_password_isAtLeast8_error.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else
            {
                iv_password_isAtLeast8_correct.setImageResource(R.drawable.ic_correct_circle_lightgray_24)
                tv_password_isAtLeast8_error.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
            }
            if (hasUppercase)
            {
                iv_password_hasUppercase_correct.setImageResource(R.drawable.ic_correct_circle_green_24)
                tv_password_hasUppercase_error.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else
            {
                iv_password_hasUppercase_correct.setImageResource(R.drawable.ic_correct_circle_lightgray_24)
                tv_password_hasUppercase_error.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
            }
            if (hasNumber)
            {
                iv_password_hasNumber_correct.setImageResource(R.drawable.ic_correct_circle_green_24)
                tv_password_hasNumber_error.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else
            {
                iv_password_hasNumber_correct.setImageResource(R.drawable.ic_correct_circle_lightgray_24)
                tv_password_hasNumber_error.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
            }
            if (hasSymbol)
            {
                iv_password_hasSymbol_correct.setImageResource(R.drawable.ic_correct_circle_green_24)
                tv_password_hasSymbol_error.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else
            {
                iv_password_hasSymbol_correct.setImageResource(R.drawable.ic_correct_circle_lightgray_24)
                tv_password_hasSymbol_error.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
            }

            if(isAtLeast8 && hasUppercase && hasNumber && hasSymbol)
            {
                tv_password_next.setTextColor(ContextCompat.getColor(this, R.color.blue))

                start_materialCardView_password.isEnabled = true
            }
            else
            {
                tv_password_next.setTextColor(ContextCompat.getColor(this, R.color.light_gray))

                start_materialCardView_password.isEnabled = false
            }
        })
    }
}
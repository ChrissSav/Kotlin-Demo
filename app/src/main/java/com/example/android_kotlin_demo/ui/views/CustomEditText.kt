package com.example.android_kotlin_demo.ui.views

import android.content.Context
import android.graphics.Typeface
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.android_kotlin_demo.R
import com.example.android_kotlin_demo.databinding.CustomEditTextBinding
import com.example.android_kotlin_demo.util.*


class CustomEditText(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var hint = ""
    private var binding = CustomEditTextBinding.inflate(LayoutInflater.from(context), this, true)
    private var isEditable = false
    private var imeOptions = 0
    private var inputType = 0
    private var textAllCaps = true
    private var maxLength = 50
    private var regex = "^.{50}\$"
    private var label: String? = null
    private var errorMessage = ""
    private var correct = false
    private var passwordIsHide = false
    private var checkText = false


    var customEditTextActionListener: CustomEditTextActionListener? = null
    var customEditTextOnClickListener: CustomEditTextOnClickListener? = null

    var text: String? = null
        get() = binding.editText.text.getString()
        set(value) {
            field = value
            binding.editText.setText(value)
        }

    var length: Int = 0
        get() = binding.editText.text.getLength()
        set(value) {
            field = value
            binding.editText.setText(value)
        }


    init {

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText)
        try {
            hint = attributes.getString(R.styleable.CustomEditText_hint).toString()
            isEditable = attributes.getBoolean(R.styleable.CustomEditText_editable, true)
            inputType =
                attributes.getInteger(R.styleable.CustomEditText_text_type, InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)
            imeOptions = attributes.getInteger(R.styleable.CustomEditText_imeOptions, EditorInfo.IME_ACTION_NEXT)
            textAllCaps = attributes.getBoolean(R.styleable.CustomEditText_all_caps, false)
            maxLength = attributes.getInteger(R.styleable.CustomEditText_max_length, 50)
            regex = attributes.getString(R.styleable.CustomEditText_regex) ?: resources.getString(R.string.regex_accept_all)
            errorMessage = attributes.getString(R.styleable.CustomEditText_error_message) ?: resources.getString(R.string.check_field)
            label = attributes.getString(R.styleable.CustomEditText_label)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        attributes.recycle()


        binding.editText.imeOptions = imeOptions
        binding.editText.inputType = inputType
        binding.editText.hint = hint

        if (inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            binding.showHide.visibility = View.VISIBLE
            binding.editText.typeface = Typeface.DEFAULT
            binding.editText.transformationMethod = PasswordTransformationMethod()
        }

        if (textAllCaps)
            binding.editText.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS

        binding.editText.filters = arrayOf<InputFilter>(LengthFilter(maxLength))


        if (!isEditable) {
            binding.editText.keyListener = null
            binding.editText.isClickable = false
            binding.editText.isFocusable = false
            binding.editText.isLongClickable = false
            binding.editText.isFocusableInTouchMode = false
            binding.editText.isCursorVisible = false
        }


        binding.editText.hint = hint
        binding.label.text = hint

        label?.let {
            binding.label.text = it
        }



        binding.editText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                customEditTextActionListener?.onTextChange(editable)
                if (checkText)
                    checkText()
            }

        })



        binding.editText.setOnClickListener {
            customEditTextOnClickListener?.onClick()
        }
        binding.root.setOnClickListener {
            customEditTextOnClickListener?.onClick()
        }

        binding.showHide.setOnClickListener {
            if (passwordIsHide) {
                binding.editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.editText.transformationMethod = PasswordTransformationMethod()
                binding.showHide.text = context.getString(R.string.show_password)
            } else {
                binding.editText.inputType = InputType.TYPE_CLASS_TEXT
                binding.editText.transformationMethod = null
                binding.showHide.text = context.getString(R.string.hide_password)
            }

            binding.editText.setSelection(binding.editText.text.length)
            passwordIsHide = !passwordIsHide
        }

        binding.error.text = errorMessage


    }

    private fun checkText() {
        val text = binding.editText.text.getString() ?: ""
        if (checkText)
            if (inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) {
                if (!isValidEmail(text)) {
                    binding.error.visibleAnim()
                    correct = false
                } else {
                    correct = true
                    binding.error.goneAnim()
                }
            } else if (!isValidRegex(text, regex)) {
                binding.error.text = errorMessage
                binding.error.visibleAnim()
                correct = false
            } else {
                correct = true
                binding.error.goneAnim()
            }
    }


    fun isCorrect(): Boolean {
        checkText = true
        checkText()
        return correct
    }


}

interface CustomEditTextActionListener {
    fun onTextChange(value: Editable?)
}


interface CustomEditTextOnClickListener {
    fun onClick()
}







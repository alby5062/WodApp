package it.alberto.wodapp

import android.text.TextUtils

class InputCheck {

    fun inputLogin(email: String,
                   password: String
    ): Boolean{
        return !(TextUtils.isEmpty(email) &&
                TextUtils.isEmpty(password))
    }

    fun inputSignUp(name: String,
                    surname: String,
                    email: String,
                    password: String
    ): Boolean{
        return !(TextUtils.isEmpty(name) &&
                TextUtils.isEmpty(surname) &&
                TextUtils.isEmpty(email) &&
                TextUtils.isEmpty(password))
    }

    fun inputExercise(exercise: String): Boolean{
        return !TextUtils.isEmpty(exercise)
    }

    fun inputWorkout(name: String,
                     type: String,
                     result: String
    ): Boolean{
        return !(TextUtils.isEmpty(name) &&
                TextUtils.isEmpty(type) &&
                TextUtils.isEmpty(result))
    }

    fun inputResult(result: String): Boolean{
        return !TextUtils.isEmpty(result)
    }
}
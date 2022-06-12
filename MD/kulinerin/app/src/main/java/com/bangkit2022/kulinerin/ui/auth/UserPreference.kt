package com.bangkit2022.kulinerin.ui.auth

import android.content.Context
import com.bangkit2022.kulinerin.data.User

open class UserPreference(context: Context) {

    val pref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    open fun saveUser(user: User) {
        val editor = pref.edit()
        editor.putInt(ID, user.id ?: 0)
        editor.putString(EMAIL, user.email)
        editor.putBoolean(IS_LOGIN, user.isLogin)
        editor.apply()
    }

    open fun getUser(): User {
        return User(
            id = pref.getInt(ID, 0),
            email = pref.getString(EMAIL, "").toString(),
            isLogin = pref.getBoolean(IS_LOGIN, false)
        )
    }

    open fun clearUser() {
        val editor = pref.edit()
        editor.clear().apply()
    }

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val ID = "id"
        private const val EMAIL = "email"
        private const val IS_LOGIN = "is_login"

        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(context: Context): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
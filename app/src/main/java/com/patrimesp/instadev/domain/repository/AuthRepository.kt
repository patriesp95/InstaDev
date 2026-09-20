package com.patrimesp.instadev.domain.repository

interface AuthRepository {
    fun doLogin(user: String, password: String):Boolean
}
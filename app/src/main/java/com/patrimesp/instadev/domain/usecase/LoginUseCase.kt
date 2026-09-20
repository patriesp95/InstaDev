package com.patrimesp.instadev.domain.usecase

import com.patrimesp.instadev.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(val authRepository: AuthRepository) {
    operator fun invoke(user: String, password: String){
        if(user.contains("@hotmail.com")){
            return
        }
        authRepository.doLogin(user, password)
    }
}
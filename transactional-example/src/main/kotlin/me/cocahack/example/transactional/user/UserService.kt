package me.cocahack.example.transactional.user

import org.springframework.transaction.annotation.Transactional

interface UserService {
    @Transactional
    fun createUser(name: String): UserEntity
}
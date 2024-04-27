package me.cocahack.example.transactional.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun createUser(name: String): UserEntity {
        val newUser = UserEntity(name = name)
        return userRepository.save(newUser)
    }
}
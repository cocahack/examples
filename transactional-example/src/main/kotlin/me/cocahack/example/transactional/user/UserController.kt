package me.cocahack.example.transactional.user

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody req: CreateUserRequest): UserEntity {
        return userService.createUser(req.name)
    }
}

data class CreateUserRequest(val name: String)
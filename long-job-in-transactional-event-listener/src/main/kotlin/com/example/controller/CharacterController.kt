package com.example.controller

import com.example.common.rest.EmptyResponse
import com.example.common.rest.Response
import com.example.entity.CharacterEntity
import com.example.entity.CharacterStatus
import com.example.service.CharacterService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/characters")
class CharacterController(private val characterService: CharacterService) {

    @PostMapping
    fun createCharacter(@RequestBody req: CreateCharacterRequest): Response<CreateCharacterResponse> {
        val result = characterService.createCharacter(req.name)

        return Response(
            data = CreateCharacterResponse(result.id, result.name, result.status)
        )
    }

    @PutMapping
    fun processCharacter(@RequestBody req: ProcessCharacterRequest): EmptyResponse {
        characterService.processCharacter(req.id)
        return EmptyResponse(status = HttpStatus.ACCEPTED)
    }

    @GetMapping("/{characterId}")
    fun getCharacter(@PathVariable characterId: Long): Response<CharacterResponse> {
        return Response(characterService.getCharacter(characterId))
    }
}

data class CreateCharacterRequest(
    val name: String
)

data class CreateCharacterResponse(
    val id: Long,
    val name: String,
    val status: CharacterStatus
)

data class ProcessCharacterRequest(
    val id: Long
)

data class CharacterResponse(
    val id: Long,
    val name: String,
    val status: CharacterStatus,
    val message: OutboxMessageResponse?,
)

data class OutboxMessageResponse(
    val id: Long,
)

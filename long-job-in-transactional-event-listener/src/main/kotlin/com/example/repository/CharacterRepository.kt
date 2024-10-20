package com.example.repository

import com.example.entity.CharacterEntity
import com.example.entity.CharacterStatus
import com.example.entity.QCharacterEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository

interface CharacterRepository : JpaRepository<CharacterEntity, Long>, CustomCharacterRepository {
    fun findByIdAndStatus(id: Long, status: CharacterStatus): CharacterEntity?
}

interface CustomCharacterRepository {
//    fun findByIdAndStatus(id: Long, status: CharacterStatus): CharacterEntity?
}

class CustomCharacterRepositoryImpl(private val jpaQueryFactory: JPAQueryFactory) : CustomCharacterRepository {
//    override fun findByIdAndStatus(id: Long, status: CharacterStatus): CharacterEntity? {
//        val character = QCharacterEntity.characterEntity
//
//        return jpaQueryFactory.selectFrom(character)
//            .where(character.id.eq(id))
//            .where(character.status.eq(status))
//            .fetchFirst()
//    }
}
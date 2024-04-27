package me.cocahack.example.transactional.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@Entity(name = "users")
class UserEntity(
    val name: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0L

    @CreatedBy
    @Column(updatable = false)
    var createdBy: String? = null
        protected set

    @CreatedDate
    @Column(updatable = false)
    var createdAt: Instant = Instant.now()
        protected set

    @LastModifiedBy
    var modifiedBy: String? = null
        protected set

    @LastModifiedDate
    var modifiedAt: Instant = Instant.now()
        protected set
}
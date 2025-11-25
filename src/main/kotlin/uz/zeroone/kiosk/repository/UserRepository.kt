package uz.zeroone.kiosk.repository

import org.springframework.data.jpa.repository.JpaRepository
import uz.zeroone.kiosk.entity.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}

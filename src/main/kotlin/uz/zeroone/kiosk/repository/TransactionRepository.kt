package uz.zeroone.kiosk.repository

import org.springframework.data.jpa.repository.JpaRepository
import uz.zeroone.kiosk.entity.Transaction

interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findAllByUserIdOrderByDateDesc(userId: Long): List<Transaction>
}

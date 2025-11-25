package uz.zeroone.kiosk.repository

import org.springframework.data.jpa.repository.JpaRepository
import uz.zeroone.kiosk.entity.TransactionItem

interface TransactionItemRepository : JpaRepository<TransactionItem, Long> {

    fun findAllByTransactionId(transactionId: Long): List<TransactionItem>

    fun findAllByTransactionUserId(userId: Long): List<TransactionItem>
}

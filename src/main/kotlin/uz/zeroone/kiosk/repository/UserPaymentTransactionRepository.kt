package uz.zeroone.kiosk.repository

import org.springframework.data.jpa.repository.JpaRepository
import uz.zeroone.kiosk.entity.UserPaymentTransaction

interface UserPaymentTransactionRepository :
    JpaRepository<UserPaymentTransaction, Long> {

    fun findAllByUserIdOrderByDateDesc(userId: Long): List<UserPaymentTransaction>
}

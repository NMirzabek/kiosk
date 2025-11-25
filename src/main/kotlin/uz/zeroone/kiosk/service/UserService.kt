package uz.zeroone.kiosk.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uz.zeroone.kiosk.entity.User
import uz.zeroone.kiosk.entity.UserPaymentTransaction
import uz.zeroone.kiosk.repository.UserPaymentTransactionRepository
import uz.zeroone.kiosk.repository.UserRepository
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository,
    private val paymentRepository: UserPaymentTransactionRepository
) {
    fun getUser(id: Long): User =
        userRepository.findById(id)
            .orElseThrow { IllegalArgumentException("User not found") }

    fun getAllUsers(): List<User> = userRepository.findAll()


    fun createUser(user: User): User = userRepository.save(user)

    fun deleteUser(id: Long) = userRepository.deleteById(id)

    @Transactional
    fun deposit(userId: Long, amount: BigDecimal): User {
        if (amount <= BigDecimal.ZERO)
            throw IllegalArgumentException("Amount must be greater than zero")

        val user = getUser(userId)

        user.balance = user.balance.add(amount)

        paymentRepository.save(
            UserPaymentTransaction(
                user = user,
                amount = amount,
                date = LocalDateTime.now()
            )
        )
        return user
    }

    fun getDepositHistory(userId: Long) =
        paymentRepository.findAllByUserIdOrderByDateDesc(userId)
}
package uz.zeroone.kiosk.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uz.zeroone.kiosk.entity.Transaction
import uz.zeroone.kiosk.entity.TransactionItem
import uz.zeroone.kiosk.repository.*
import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class TransactionService(
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val transactionRepository: TransactionRepository,
    private val transactionItemRepository: TransactionItemRepository
) {
    @Transactional
    fun purchase(userId: Long, items: List<Pair<Long, Long>>): Transaction {

        val user = userRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("User not found") }

        val transaction = transactionRepository.save(
            Transaction(
                user = user,
                date = LocalDateTime.now(),
                totalAmount = BigDecimal.ZERO
            )
        )

        var total = BigDecimal.ZERO

        items.forEach { (productId, count) ->
            val product = productRepository.findById(productId)
                .orElseThrow { IllegalArgumentException("Product not found") }

            if (product.count < count)
                throw IllegalArgumentException("Not enough stock for product: ${product.name}")

            val price = BigDecimal(productId)

            val itemTotal = price.multiply(BigDecimal(count))

            val item = TransactionItem(
                product = product,
                count = count,
                amount = price,
                totalAmount = itemTotal,
                transaction = transaction
            )

            transactionItemRepository.save(item)

            product.count -= count
            productRepository.save(product)

            total += itemTotal
        }

        if (user.balance < total)
            throw IllegalArgumentException("Insufficient balance")

        user.balance -= total
        userRepository.save(user)

        transaction.totalAmount = total
        return transactionRepository.save(transaction)
    }

    fun getUserTransactions(userId: Long) =
        transactionRepository.findAllByUserIdOrderByDateDesc(userId)

    fun getTransactionItems(transactionId: Long) =
        transactionItemRepository.findAllByTransactionId(transactionId)

    fun getUserAllPurchasedItems(userId: Long) =
        transactionItemRepository.findAllByTransactionUserId(userId)

    fun getAllTransactions() = transactionRepository.findAll()
}
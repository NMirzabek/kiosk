package uz.zeroone.kiosk.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
import uz.zeroone.kiosk.dto.PurchaseRequestDto
import uz.zeroone.kiosk.dto.PurchaseResponseDto
import uz.zeroone.kiosk.repository.TransactionRepository
import uz.zeroone.kiosk.service.TransactionService

@RestController
@RequestMapping("/api/transactions")

class TransactionController(
    private val transactionService: TransactionService
) {

    @PostMapping("/purchase")
    fun purchase(@RequestBody request: PurchaseRequestDto): PurchaseResponseDto {
        val transaction = transactionService.purchase(
            request.userId,
            request.items.map { it.productId to it.count }
        )

        return PurchaseResponseDto(
            transactionId = transaction.id!!,
            userId = transaction.user.id!!,
            totalAmount = transaction.totalAmount,
            date = transaction.date
        )
    }

    @GetMapping("/user/{userId}")
    fun getUserTransactions(@PathVariable userId: Long): List<PurchaseResponseDto> {
        return transactionService.getUserTransactions(userId).map {
            PurchaseResponseDto(
                transactionId = it.id!!,
                userId = it.user.id!!,
                totalAmount = it.totalAmount,
                date = it.date
            )
        }
    }

    @GetMapping("/{transactionId}/items")
    fun getTransactionItems(@PathVariable transactionId: Long) =
        transactionService.getTransactionItems(transactionId)

    @GetMapping("/user/{userId}/items")
    fun getUserAllPurchasedItems(@PathVariable userId: Long) =
        transactionService.getUserAllPurchasedItems(userId)

    @GetMapping
    fun getAllTransactions(): List<PurchaseResponseDto> {
        return transactionService.getAllTransactions().map {
            PurchaseResponseDto(
                transactionId = it.id!!,
                userId = it.user.id!!,
                totalAmount = it.totalAmount,
                date = it.date
            )
        }
    }
}
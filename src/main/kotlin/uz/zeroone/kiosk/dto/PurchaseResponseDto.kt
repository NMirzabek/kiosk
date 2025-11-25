package uz.zeroone.kiosk.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class PurchaseResponseDto(
    val transactionId: Long,
    val userId: Long,
    val totalAmount: BigDecimal,
    val date: LocalDateTime
)

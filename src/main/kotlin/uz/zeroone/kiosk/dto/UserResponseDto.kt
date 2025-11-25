package uz.zeroone.kiosk.dto

import java.math.BigDecimal

data class UserResponseDto(
    val id: Long,
    val fullname: String,
    val username: String,
    val balance: BigDecimal
)

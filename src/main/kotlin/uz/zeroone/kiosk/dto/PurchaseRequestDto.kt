package uz.zeroone.kiosk.dto

data class PurchaseRequestDto(
    val userId: Long,
    val items: List<PurchaseItemDto>
)

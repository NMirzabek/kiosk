package uz.zeroone.kiosk.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "transaction_item")
class TransactionItem(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product,

    var count: Long,

    @Column(precision = 19, scale = 2, nullable = false)
    var amount: BigDecimal,

    @Column(name = "total_amount", precision = 19, scale = 2, nullable = false)
    var totalAmount: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    var transaction: Transaction
) : BaseEntity()
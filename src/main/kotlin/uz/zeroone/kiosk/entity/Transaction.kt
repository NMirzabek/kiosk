package uz.zeroone.kiosk.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transaction")
class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @Column(name = "total_amount", precision = 19, scale = 2, nullable = false)
    var totalAmount: BigDecimal = BigDecimal.ZERO,

    var date: LocalDateTime = LocalDateTime.now()
)

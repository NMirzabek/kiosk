package uz.zeroone.kiosk.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "user_payment_transaction")
class UserPaymentTransaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,

    @Column(precision = 19, scale = 2, nullable = false)
    var amount: BigDecimal,

    var date: LocalDateTime = LocalDateTime.now()
)

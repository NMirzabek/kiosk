package uz.zeroone.kiosk.entity

import jakarta.annotation.Generated
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var fullname: String,

    @Column(unique = true, nullable = false)
    var username: String,

    @Column(precision = 19, scale = 2, nullable = false)
    var balance: BigDecimal = BigDecimal.ZERO
)
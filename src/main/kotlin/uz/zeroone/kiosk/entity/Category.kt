package uz.zeroone.kiosk.entity

import jakarta.persistence.*

@Entity
@Table(name = "category")
class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    @Column(name = "\"order\"")
    var order: Long? = null,

    var description: String? = null
)

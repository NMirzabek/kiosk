package uz.zeroone.kiosk.entity

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    var count: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: Category
)

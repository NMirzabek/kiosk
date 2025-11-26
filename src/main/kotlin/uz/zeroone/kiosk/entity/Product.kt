package uz.zeroone.kiosk.entity

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product(

    var name: String,

    var count: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: Category
) : BaseEntity()

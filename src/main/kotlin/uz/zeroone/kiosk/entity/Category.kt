package uz.zeroone.kiosk.entity

import jakarta.persistence.*

@Entity
@Table(name = "category")
class Category(

    var name: String,

    @Column(name = "\"order\"")
    var order: Long? = null,

    var description: String? = null
) : BaseEntity()


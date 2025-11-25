package uz.zeroone.kiosk.repository

import org.springframework.data.jpa.repository.JpaRepository
import uz.zeroone.kiosk.entity.Product

interface ProductRepository : JpaRepository<Product, Long>

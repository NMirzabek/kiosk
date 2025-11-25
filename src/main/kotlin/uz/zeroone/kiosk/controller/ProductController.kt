package uz.zeroone.kiosk.controller

import org.springframework.web.bind.annotation.*
import uz.zeroone.kiosk.entity.Product
import uz.zeroone.kiosk.repository.ProductRepository

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productRepository: ProductRepository
) {

    @GetMapping
    fun getAll(): List<Product> =
        productRepository.findAll()

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): Product =
        productRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Product not found") }

    @PostMapping
    fun create(@RequestBody product: Product): Product =
        productRepository.save(product)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody product: Product
    ): Product {
        if (!productRepository.existsById(id)) {
            throw IllegalArgumentException("Product not found")
        }
        product.id = id
        return productRepository.save(product)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        productRepository.deleteById(id)
}

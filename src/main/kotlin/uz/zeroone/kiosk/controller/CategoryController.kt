package uz.zeroone.kiosk.controller

import org.springframework.web.bind.annotation.*
import uz.zeroone.kiosk.entity.Category
import uz.zeroone.kiosk.repository.CategoryRepository

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val categoryRepository: CategoryRepository
) {

    @GetMapping
    fun getAll(): List<Category> =
        categoryRepository.findAll()

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): Category =
        categoryRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Category not found") }

    @PostMapping
    fun create(@RequestBody category: Category): Category =
        categoryRepository.save(category)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody category: Category
    ): Category {
        if (!categoryRepository.existsById(id)) {
            throw IllegalArgumentException("Category not found")
        }
        category.id = id
        return categoryRepository.save(category)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        categoryRepository.deleteById(id)
}

package uz.zeroone.kiosk.controller

import org.springframework.web.bind.annotation.*
import uz.zeroone.kiosk.dto.*
import uz.zeroone.kiosk.entity.User
import uz.zeroone.kiosk.service.UserService
import java.math.BigDecimal

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun getAllUsers() =
        userService.getAllUsers().map {
            UserResponseDto(
                id = it.id!!,
                fullname = it.fullname,
                username = it.username,
                balance = it.balance
            )
        }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): UserResponseDto {
        val user = userService.getUser(id)
        return UserResponseDto(
            id = user.id!!,
            fullname = user.fullname,
            username = user.username,
            balance = user.balance
        )
    }

    @PostMapping
    fun createUser(@RequestBody dto: UserCreateDto): UserResponseDto {
        val user = User(
            fullname = dto.fullname,
            username = dto.username,
            balance = BigDecimal.ZERO
        )
        val saved = userService.createUser(user)

        return UserResponseDto(
            id = saved.id!!,
            fullname = saved.fullname,
            username = saved.username,
            balance = saved.balance
        )
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) =
        userService.deleteUser(id)

    @PostMapping("/{id}/deposit")
    fun deposit(
        @PathVariable id: Long,
        @RequestBody dto: DepositRequestDto
    ): UserResponseDto {

        val updated = userService.deposit(id, dto.amount)

        return UserResponseDto(
            id = updated.id!!,
            fullname = updated.fullname,
            username = updated.username,
            balance = updated.balance
        )
    }

    @GetMapping("/{id}/deposits")
    fun depositHistory(@PathVariable id: Long) =
        userService.getDepositHistory(id)
}
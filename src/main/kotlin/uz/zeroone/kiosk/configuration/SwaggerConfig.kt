package uz.zeroone.kiosk.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Kiosk API Documentation")
                    .version("1.0.0")
                    .description(
                        """
                        This is the API documentation for the Kiosk project.
                        Contains User, Product, Category, Transactions and other modules.
                        """.trimIndent()
                    )
                    .contact(
                        Contact()
                            .name("Developer: Mirzakhid")
                            .email("example@gmail.com")
                            .url("https://github.com/your-profile")
                    )
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("https://springdoc.org")
                    )
            )
    }
}

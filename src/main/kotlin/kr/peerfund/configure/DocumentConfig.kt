package kr.peerfund.configure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class DocumentConfig {
    fun apiKey(): ApiKey? {
        return ApiKey("JWT", HttpHeaders.AUTHORIZATION, "header")
    }

    fun securityContext(): SecurityContext? {
        return SecurityContext.builder().securityReferences(defaultAuth()).build()
    }

    fun defaultAuth(): List<SecurityReference?>? {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return listOf(SecurityReference("JWT", authorizationScopes))
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .securityContexts(listOf(securityContext()))
            .securitySchemes(listOf(apiKey()))
            .select()
            .apis(RequestHandlerSelectors.basePackage("kr.peerfund"))
//            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
    }

    private fun getApiInfo(): ApiInfo {
        val contact = Contact(
            "peerfund",
            "https://github.com/SPARCS-2023-StartUp-Hackathon-3/team-p-peerfund-be",
            "dnstlr2933@gmail.com"
        )
        return ApiInfoBuilder()
            .title("peerfund Api")
            .description("api for peerfund service")
            .version("1.0.0")
            .contact(contact)
            .build()
    }
}

package kr.peerfund.configure

import org.hibernate.validator.constraints.Length
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt-security")
class SecurityProperties {
    @Length(min = 20, message = "Minimum length for the secret is 20.")
    var secret = ""
    var expirationTime: Int = 60 // in minute
    var tokenPrefix = "Bearer "
    var headerString = "Authorization"
    var authorityKey = "role"
    var strength = 10
}

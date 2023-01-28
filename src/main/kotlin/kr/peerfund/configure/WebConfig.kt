package kr.peerfund.configure

import kr.peerfund.jwt.filter.JwtAuthenticationFilter
import kr.peerfund.jwt.service.AppUserDetailsService
import kr.peerfund.jwt.service.JwtTokenProvider
import kr.peerfund.role.dto.RoleType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.PathRequest.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
class WebConfig(
    val bCryptPasswordEncoder: BCryptPasswordEncoder,
    val userDetailsService: AppUserDetailsService,
    val securityProperties: SecurityProperties
) : WebSecurityConfigurerAdapter(), WebMvcConfigurer {

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager = super.authenticationManagerBean()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Autowired
    lateinit var jwtTokenProvider: JwtTokenProvider

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .mvcMatchers("/auth/sign-up")
        web.ignoring()
            .requestMatchers(toStaticResources().atCommonLocations()); // 정적인 리소스들에 대해서 시큐리티 적용 무시.
    }

    override fun configure(http: HttpSecurity) {
        http
            .cors().configurationSource(corsConfigurationSource()).and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no sessions
            .and()
            .authorizeRequests()
            /* Swagger */
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/v2/api-docs").permitAll()
            /* Default */
            .antMatchers("/admin/**").hasAuthority(RoleType.ROLE_ADMIN.toString())
            .antMatchers("/user/**").hasAnyAuthority(RoleType.ROLE_USER.toString(), RoleType.ROLE_ADMIN.toString())
            .antMatchers("/error/**").permitAll()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(
                JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder)
    }

    @Bean
    fun authProvider(): DaoAuthenticationProvider = DaoAuthenticationProvider().apply {
        setUserDetailsService(userDetailsService)
        setPasswordEncoder(bCryptPasswordEncoder)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource = UrlBasedCorsConfigurationSource().also { cors ->
        CorsConfiguration().apply {
            allowedMethods = listOf("POST", "PUT", "DELETE", "GET", "OPTIONS", "HEAD")
            allowedHeaders = listOf(
                securityProperties.headerString,
                "Content-Type",
                "X-Requested-With",
                "Accept",
                "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"
            )
            exposedHeaders = listOf(
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials",
                securityProperties.headerString,
                "Content-Disposition"
            )
            allowedOrigins = listOf(
                "http://peerfund.hackathon.sparcs.org",
                "http://localhost",
                "http://localhost:3000",
                "http://ec2-54-180-159-18.ap-northeast-2.compute.amazonaws.com",
            )
            maxAge = 3600
            cors.registerCorsConfiguration("/**", this)
        }
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:3000",
                "http://ec2-54-180-159-18.ap-northeast-2.compute.amazonaws.com",
                "http://peerfund.hackathon.sparcs.org",
            )
            .allowedMethods("*")
            .allowedOriginPatterns("*")
            .allowCredentials(true)
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("http://localhost")
        config.addAllowedOrigin("http://peerfund.hackathon.sparcs.org")
        config.addAllowedOrigin("http://localhost:3000")
        config.addAllowedOrigin("http://ec2-54-180-159-18.ap-northeast-2.compute.amazonaws.com")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        config.addExposedHeader("Authorization")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}

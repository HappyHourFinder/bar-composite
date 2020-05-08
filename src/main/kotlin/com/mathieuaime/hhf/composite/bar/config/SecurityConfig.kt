package com.mathieuaime.hhf.composite.bar.config

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter

@Configuration
@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll()
        // Validate tokens through configured OpenID Provider
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
        // Require authentication for all requests
        http.authorizeRequests().anyRequest().authenticated()
        // Allow showing pages within a frame
        http.headers().frameOptions().sameOrigin()
    }

    private fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        // Convert realm_access.roles claims to granted authorities, for use in access decisions
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(KeycloakRealmRoleConverter())
        return jwtAuthenticationConverter
    }

    @Bean
    fun jwtDecoderByIssuerUri(properties: OAuth2ResourceServerProperties): JwtDecoder {
        val issuerUri = properties.jwt.issuerUri
        val jwtDecoder = JwtDecoders.fromIssuerLocation(issuerUri) as NimbusJwtDecoder
        // Use preferred_username from claims as authentication name, instead of UUID subject
        jwtDecoder.setClaimSetConverter(UsernameSubClaimAdapter())
        return jwtDecoder
    }
}

private class UsernameSubClaimAdapter : Converter<Map<String, Any>, Map<String, Any>> {
    private val delegate = MappedJwtClaimSetConverter.withDefaults(emptyMap())

    override fun convert(claims: Map<String, Any>): Map<String, Any> {
        val convertedClaims = delegate.convert(claims)
        convertedClaims!!["sub"] = convertedClaims["preferred_username"] as String
        return convertedClaims
    }
}

private class KeycloakRealmRoleConverter : Converter<Jwt, Collection<GrantedAuthority>> {
    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val realmAccess = jwt.claims["realm_access"] as Map<*, *>
        val roles = realmAccess["roles"] as List<*>
        return roles.map { roleName -> SimpleGrantedAuthority("ROLE_$roleName") }
    }
}
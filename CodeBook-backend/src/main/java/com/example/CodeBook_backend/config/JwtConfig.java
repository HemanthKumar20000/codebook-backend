package com.example.CodeBook_backend.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Configuration
public class JwtConfig {

    @Bean
    public KeyPair keyPair() throws Exception {

        KeyPairGenerator generator =
                KeyPairGenerator.getInstance("RSA");

        generator.initialize(2048);

        return generator.generateKeyPair();
    }

    @Bean
    public JwtEncoder jwtEncoder(KeyPair keyPair) {

        JWK jwk = new RSAKey.Builder(
                (java.security.interfaces.RSAPublicKey) keyPair.getPublic()
        )
                .privateKey(
                        (java.security.interfaces.RSAPrivateKey) keyPair.getPrivate()
                )
                .build();

        JWKSource<SecurityContext> jwkSource =
                new ImmutableJWKSet<>(new JWKSet(jwk));

        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder(KeyPair keyPair) {

        return NimbusJwtDecoder
                .withPublicKey(
                        (java.security.interfaces.RSAPublicKey) keyPair.getPublic()
                )
                .build();
    }
}
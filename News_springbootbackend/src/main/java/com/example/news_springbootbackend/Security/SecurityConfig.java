package com.example.news_springbootbackend.Security;

import com.example.news_springbootbackend.service.JpaUserDetailsService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JpaUserDetailsService myUserDetailsService;
    private  final RsaKeyProp rsakeys;
    public SecurityConfig(JpaUserDetailsService myUserDetailsService, RsaKeyProp rsakeys) {
        this.myUserDetailsService = myUserDetailsService;
        this.rsakeys = rsakeys;
    }
    //@Override
    //protected  boolean shouldNotFilter(HttpServletRequest request) throws SecurityException{
    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
        return http
                //if used csrf.ignoringAntMatchers -->prevent the attack from cross-origin
                //.csrf(csrf -> csrf.disable())
                .csrf(csrf -> csrf.ignoringAntMatchers("/shownews/**","/getnews/**","/token"))

                .authorizeRequests(auth-> auth
                        .antMatchers("/shownews/**").permitAll()
                        .antMatchers("/getnews/**").permitAll()
                        .anyRequest().authenticated())

                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //sessionmanagement can direct the client to specific url if they have wrong pw
                .userDetailsService(myUserDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .httpBasic(Customizer.withDefaults())
                .formLogin().and()
                .build();
    }
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsakeys.publicKey()).build();
    }
    //openssl genrsa -out keypair.pem 2048
    // openssl rs -in keypair.pem -pubout -out public.pem
    //openssl rsa -in keypair.pem -pubout -out public.pem
    //openssl pkcs8 -topk -inform PEM -out -nocrypt -in keypair.pem -out private.pem
    // openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk=new RSAKey.Builder(rsakeys.publicKey()).privateKey(rsakeys.privateKey()).build();
        JWKSource<SecurityContext> jwks=new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}


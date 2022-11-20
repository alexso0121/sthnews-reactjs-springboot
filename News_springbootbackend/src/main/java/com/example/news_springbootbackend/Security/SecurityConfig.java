package com.example.news_springbootbackend.Security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

//main config for spring boot security
/*
 blocking the all access to the springboot server by springboot security except the url
 for authentication page and shownews and articles.
 the secured urls can only access by using the json web token (jwb)
 the signup and token(login) urls can generate the token after authentication
*/

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JpaUserDetailsService myUserDetailsService;
    private  final RsaKeyProp rsakeys;


    boolean webSecurityDebug;

    public SecurityConfig(JpaUserDetailsService myUserDetailsService, RsaKeyProp rsakeys) {
        this.myUserDetailsService = myUserDetailsService;
        this.rsakeys = rsakeys;
    }

    //adding the debug log file
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.debug(webSecurityDebug);
    }

    //main configuration bean for springboot security
    //configuring method for accessing the springboot server
    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
        return http
               //allow cross-origin
                .cors().and()
                //allowing crsf with exception
                .csrf(csrf -> csrf.ignoringAntMatchers("/shownews/**","/getnews/**","/token","/signup"))

                //criteria for accessing the server
                .authorizeRequests(auth-> auth
                        //the urls that ignore by springboot security
                        .antMatchers("/shownews/**").permitAll()
                        .antMatchers("/getnews/**").permitAll()
                        .antMatchers("/signup").permitAll()

                        //need to authenticate by bearer token (jwt)
                        .anyRequest().authenticated())

                //enable the authentication by jwt
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Link to the jpauserservice to check whether the authentication valid (similar to authentication manager)
                .userDetailsService(myUserDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .httpBasic(Customizer.withDefaults())
                //.formLogin().and()
                .build();
    }
    //allow the authentication manager to convert the entity model to authentication model for authentication
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)  {
        try{System.out.println(authConfig.getAuthenticationManager());
        return authConfig.getAuthenticationManager();}catch(Exception exception){
            System.out.println(exception);
            return null;
        }
    }

    //configuration decoding the jwt
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsakeys.publicKey()).build();
    }

    //encode the jwt for generating token
    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk=new RSAKey.Builder(rsakeys.publicKey()).privateKey(rsakeys.privateKey()).build();
        JWKSource<SecurityContext> jwks=new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
    //encrypt the password before storing to the database
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
//command line generating the asymmetrical keypairs for generating token
/*openssl genrsa -out keypair.pem 2048
openssl rs -in keypair.pem -pubout -out public.pem
openssl rsa -in keypair.pem -pubout -out public.pem
openssl pkcs8 -topk -inform PEM -out -nocrypt -in keypair.pem -out private.pem
 openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem*/


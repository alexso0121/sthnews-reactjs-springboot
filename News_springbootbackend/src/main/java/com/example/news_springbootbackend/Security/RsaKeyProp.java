package com.example.news_springbootbackend.Security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

//class for setting the asymmetrical keys
@ConfigurationProperties(prefix = "rsa")
public record RsaKeyProp
        (RSAPublicKey publicKey, RSAPrivateKey privateKey){
}

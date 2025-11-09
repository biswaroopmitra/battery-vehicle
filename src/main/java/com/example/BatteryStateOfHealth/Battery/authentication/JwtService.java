package com.example.BatteryStateOfHealth.Battery.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public final String ENCODE_KEY = Base64.getEncoder().encodeToString(key.getEncoded());

    public String createJwt(String username){
        Map<String, Object> claims = new HashMap<>();
        return token(claims, username);
    }

    private String token(Map<String, Object> claims, String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) //1hour
                .signWith(getKeyForSigning(), SignatureAlgorithm.HS256).compact();
    }

    private Key getKeyForSigning(){
        byte[] keyBytesValue = Decoders.BASE64.decode(ENCODE_KEY);
        return Keys.hmacShaKeyFor(keyBytesValue);
    }

    public void validateJwt(final String token){
        Jwts.parserBuilder()
            .setSigningKey(getKeyForSigning())
            .build()
            .parseClaimsJws(token);
    }
}

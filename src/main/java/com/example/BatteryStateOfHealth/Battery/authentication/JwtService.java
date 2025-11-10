package com.example.BatteryStateOfHealth.Battery.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    //SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    public final String ENCODE_KEY = Base64.getEncoder().encodeToString(key.getEncoded());
    public static final String ENCODE_KEY = "2367566B59703373367639792F423F4528482B4D6251655468576D5A71347932";

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

    //public void validateJwt(final String token){
    //    Jwts.parserBuilder()
    //        .setSigningKey(getKeyForSigning())
    //        .build()
    //        .parseClaimsJws(token);
    //}

    public Claims extractClaims(final String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKeyForSigning())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractClaims(token);
        return claimsResolver.apply((claims));
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return  extractClaim(token, Claims::getExpiration);
    }

    public Boolean isTokenPastValidity(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateJwt(final String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenPastValidity(token));
    }
}

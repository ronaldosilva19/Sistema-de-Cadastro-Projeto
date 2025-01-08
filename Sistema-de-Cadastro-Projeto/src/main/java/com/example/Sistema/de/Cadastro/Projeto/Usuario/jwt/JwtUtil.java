package com.example.Sistema.de.Cadastro.Projeto.Usuario.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
    private final String SECRET = "mySecretKey@12345$SuperSecureKey";

    public String gerarToken(String username, List<String> roles){
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia.
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String extrairUsername(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validarToken(String token, UserDetails userDetails){
        final String username = extrairUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpirado(token);
    }

    public boolean isTokenExpirado(String toekn){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(toekn).getBody().getExpiration().before(new Date());
    }
}

package arutala.backend.bookrecipe.util;

import arutala.backend.bookrecipe.model.MyUserDetails;
import arutala.backend.bookrecipe.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class Jwt {
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.access.expiration}")
    private Long JWT_EXPIRATION;

//    @Value("${jwt.refresh.expiration}")
//    private Long refreshTokenExpirationMs;

    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;

    @PostConstruct
    void init() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        this.jwtBuilder = Jwts.builder()
                .signWith(secretKey, SignatureAlgorithm.HS256);

        this.jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();
    }

    public String generateToken(User user) {
        return jwtBuilder
                .subject(user.getId().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .compact();
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String JWT_HEADER = "Authorization";
        String JWT_PREFIX = "Bearer";

        String bearerToken = request.getHeader(JWT_HEADER);

        if (bearerToken != null && bearerToken.startsWith(JWT_PREFIX)) {
            return bearerToken.substring(JWT_PREFIX.length()+1);
        }

        return null;
    }

    private Claims validateToken(String token) {
        return jwtParser.parseSignedClaims(token).getPayload();
    }

    public Integer extractUserId(Claims claims) {
        return Integer.parseInt(claims.getSubject());
    }

    private Boolean isExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public Claims resolveJwt(HttpServletRequest request) {
        try {
            String token = extractTokenFromHeader(request);

            if (token != null) {
                return validateToken(token);
            }

            return null;
        } catch (Exception e) {
            throw  e;
        }
    }

}

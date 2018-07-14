package br.com.douglasffilho.oauthapplication.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtTokenUtils {

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_ROLE = "role";

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(final String token) {
        try {
            final Optional<Claims> claims = Optional.ofNullable(this.getClaimsFromToken(token));
            if (claims.isPresent())
                return claims.get().getSubject();
            return null;
        } catch (final Exception e) {
            return null;
        }
    }

    private Date getExpirationDateFromToken(final String token) {
        try {
            final Optional<Claims> claims = Optional.ofNullable(this.getClaimsFromToken(token));
            if (claims.isPresent())
                return claims.get().getExpiration();
            return null;
        } catch (final Exception e) {
            return null;
        }
    }

    public boolean isValidToken(final String token) {
        return !this.expiredToken(token);
    }

    public String getToken(final UserDetails userDetails) {
        final Map<String, Object> claims = new HashMap<>();

        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
        claims.put(CLAIM_KEY_CREATED, new Date());

        return this.generateToken(claims);
    }

    private Claims getClaimsFromToken(final String token) {
        try {
            return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        } catch (final Exception e) {
            return null;
        }
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    private boolean expiredToken(final String token) {
        final Date expirationDate = this.getExpirationDateFromToken(token);
        return expirationDate != null && expirationDate.before(new Date());
    }

    private String generateToken(final Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }
}

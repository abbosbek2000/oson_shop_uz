package uz.spring.oson_shop_uz.admin.security;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class JwtProvider {
    private static final long expireTime = 1000_000_000L * 60 * 60 * 24;
    public String generateToken(String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, "secretKey")
                .compact();
    }

    public boolean checkValidateToken(String token) {
        try {
//            Jwts
//                    .parser()
//                    .setSigningKey("secretKey")
//                    .parseClaimsJws(token);
//            return true;
            try {
                Jws<Claims> claims = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token);
                return true;
            } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
                throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
            } catch (ExpiredJwtException ex) {
                throw ex;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        String username = Jwts
                .parser()
                .setSigningKey("secretKey")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }
}

package uz.spring.oson_shop_uz.admin.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class JwtProvider {
    private static final long expireTime = 1000 * 60 * 60 * 24;
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
            Jwts
                    .parser()
                    .setSigningKey("secretKey")
                    .parseClaimsJws(token);
            return true;
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

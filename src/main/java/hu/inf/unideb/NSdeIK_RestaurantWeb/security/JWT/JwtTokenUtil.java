package hu.inf.unideb.NSdeIK_RestaurantWeb.security.JWT;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtTokenUtil{

    //Ervenyes token validacio ido
    private static final long JWT_TOKEN_VALIDITY = 5*60*60;

    @Value("${jwt.secret}")
    private String secret;

    //Felhasznalo kinyerese jwt tokenbol
    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        List<SimpleGrantedAuthority> roles = null;

        Boolean tulajdonos = claims.get("tulajdonos", Boolean.class);
        Boolean pincer = claims.get("pincer", Boolean.class);
        Boolean szakacs = claims.get("szakacs", Boolean.class);

        if (tulajdonos != null && tulajdonos) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_TULAJDONOS"));
        }

        if (pincer != null && pincer) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_PINCER"));
        }

        if (szakacs != null && szakacs) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_SZAKACS"));
        }

        return roles;
    }

    //Token letrehozas
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

        if(roles.contains(new SimpleGrantedAuthority("TULAJDONOS"))){
            claims.put("tulajdonos",true);
        }

        if(roles.contains(new SimpleGrantedAuthority("PINCER"))){
            claims.put("pincer",true);
        }

        if(roles.contains(new SimpleGrantedAuthority("SZAKACS"))){
            claims.put("szakacs",true);
        }
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //Token generalas
    private String doGenerateToken(Map<String,Object> claims, String subject){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    //Token validacio
    public Boolean validateToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
            throw ex;
        }
    }

}

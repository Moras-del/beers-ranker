package pl.moras.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import pl.moras.auth.models.Role;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds;
    private UserDetailsService userDetailsService;
    private final Key key;


    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String createToken(String username, int userId, Set<Role> roles){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        claims.put("userId", userId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    protected Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token){
        return getClaims(token).getBody().getSubject();
    }

    public int getId(String token){
        return getClaims(token).getBody().get("userId", Integer.class);
    }

    public Collection<Role> getRoles(String token){
        return getClaims(token).getBody().get("roles", Collection.class);
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = getClaims(token);
            if (claims.getBody().getExpiration().before(new Date()))
                return false;
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Jws<Claims> getClaims(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    }

}

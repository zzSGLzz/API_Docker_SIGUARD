package indiecode.api.siguard.security.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import indiecode.api.siguard.security.UserDetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${security.jwt.key.private}")
    private String privateKey;
    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    /**
     * Metodo que genera el token
     * @param authentication Usuario
     * @return token JWT
     * @throws UnsupportedEncodingException Error
     */
    public String createToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey); //Libreria JWT

        String username=((CustomUserDetails)authentication.getPrincipal()).getUsername();//Usuario para pasarlo por el token
        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        return JWT.create()
                .withIssuer(this.userGenerator)//¿Quién lo genera?
                .withSubject(username)//Entidad a la que se refiere
                .withClaim("authorities", authorities) //Authorities
                .withIssuedAt(new Date()) //Fecha de inicio
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))//Fecha de fin
                .withJWTId(UUID.randomUUID().toString())//numero id aleatorio y no repetitivo
                .withNotBefore(new Date(System.currentTimeMillis())) //Momento apartir del que el token es valido
                .sign(algorithm); //Firma
    }
    public DecodedJWT validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier=JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();
            return verifier.verify(token);

        }catch (JWTVerificationException e){
            throw  new JWTVerificationException("Token invalido");
        }
    }
    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }
    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }
    public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}

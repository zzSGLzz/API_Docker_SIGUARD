package indiecode.api.siguard.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import indiecode.api.siguard.security.Jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtValidator extends OncePerRequestFilter {
    private JwtUtils jwtUtils;

    public JwtValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken=request.getHeader(HttpHeaders.AUTHORIZATION);
        if(jwtToken!=null){
            jwtToken=jwtToken.substring(7);
            try{
                DecodedJWT decodedJWT= jwtUtils.validateToken(jwtToken);
                String username=jwtUtils.extractUsername(decodedJWT);
                String stringAuthorities=jwtUtils.getSpecificClaim(decodedJWT,"authorities").asString();

                Collection<? extends GrantedAuthority>authorities= AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);
                SecurityContext context= SecurityContextHolder.getContext();
                Authentication authentication=new UsernamePasswordAuthenticationToken(username,null,authorities);
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            }catch(Exception e){
                // Manejar el error de token inválido o malformado
                System.out.println(e.getMessage());

            }

        }
        filterChain.doFilter(request, response);
    }
}

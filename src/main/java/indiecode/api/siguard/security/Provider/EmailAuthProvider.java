package indiecode.api.siguard.security.Provider;

import indiecode.api.siguard.Persistence.Repositories.UsuarioRepository;
import indiecode.api.siguard.Persistence.Usuario;
import indiecode.api.siguard.security.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class EmailAuthProvider implements AuthenticationProvider {

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositorio de usuarios


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Usuario reqUsuario=usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new AuthenticationCredentialsNotFoundException(authentication.getName()));

        if(reqUsuario.getPassword().equals(authentication.getCredentials())){
            return new UsernamePasswordAuthenticationToken(
                    reqUsuario.getEmail(),
                    reqUsuario.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(reqUsuario.getRol().getEnumRol().name())));
        }else{
        throw new AuthenticationCredentialsNotFoundException("Error in authentication!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

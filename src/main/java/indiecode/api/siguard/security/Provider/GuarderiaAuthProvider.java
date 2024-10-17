package indiecode.api.siguard.security.Provider;

import indiecode.api.siguard.Persistence.Repositories.GuarderiasRepository;
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
public class GuarderiaAuthProvider implements AuthenticationProvider {
    @Autowired
    private GuarderiasRepository guarderiasRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Usuario reqUsuario=guarderiasRepository.findIdUsuarioGerenteById(authentication.getName()).orElseThrow(()->new RuntimeException(""));
        return new UsernamePasswordAuthenticationToken(
                reqUsuario,
                authentication.getCredentials().toString(),
                Arrays.asList(new SimpleGrantedAuthority(reqUsuario.getRol().getEnumRol().name())));

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

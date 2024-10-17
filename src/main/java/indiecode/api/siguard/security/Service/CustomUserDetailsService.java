package indiecode.api.siguard.security.Service;

import indiecode.api.siguard.Persistence.Dto.LoginRequestDto;
import indiecode.api.siguard.Persistence.Repositories.GuarderiasRepository;
import indiecode.api.siguard.Persistence.Repositories.UsuarioRepository;
import indiecode.api.siguard.Persistence.Usuario;
import indiecode.api.siguard.security.Controllers.dto.AuthRespondeDto;
import indiecode.api.siguard.security.Jwt.JwtUtils;
import indiecode.api.siguard.security.UserDetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    UsuarioRepository usuarioRepository;



    //Retriving user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new CustomUserDetails(usuario);
    }


    public AuthRespondeDto login(LoginRequestDto loginRequestDto){
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken= jwtUtils.createToken(authentication);
        return new AuthRespondeDto(username, "Usuario inicio sesión correctamente",accessToken, true);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        // Aquí deberías verificar la contraseña utilizando un PasswordEncoder
        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Contraseña Invalida");
        }
        // Devuelve el Authentication token con el UserDetails
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
}

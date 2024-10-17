package indiecode.api.siguard.security.UserDetails;

import indiecode.api.siguard.Persistence.Roles;
import indiecode.api.siguard.Persistence.Usuario;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private Usuario usuario;

    public CustomUserDetails(Usuario user) {
        this.usuario = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convierte los roles de la entidad en GrantedAuthority
       // Set<Roles> roles = usuario.getRol().getEnumRol();
        //return List.of();
        // Dado que el usuario solo tiene un rol, creamos una lista con un solo GrantedAuthority
        return Collections.singletonList(
                new SimpleGrantedAuthority(usuario.getRol().getEnumRol().name()
                )
        );
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }
}

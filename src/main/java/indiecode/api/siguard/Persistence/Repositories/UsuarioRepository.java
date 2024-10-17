/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package indiecode.api.siguard.Persistence.Repositories;

import indiecode.api.siguard.Persistence.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author zzsglzz
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByNombre(String nombre);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNombre(String nombre);
    
}

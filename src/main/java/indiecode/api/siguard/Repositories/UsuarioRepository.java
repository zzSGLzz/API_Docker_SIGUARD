/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package indiecode.api.siguard.Repositories;

import indiecode.api.siguard.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author zzsglzz
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByNombre(String nombre);
    
}

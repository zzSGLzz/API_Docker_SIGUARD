/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package indiecode.api.siguard.Persistence.Repositories;

import indiecode.api.siguard.Persistence.Guarderias;
import indiecode.api.siguard.Persistence.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 *
 * @author zzsglzz
 */
public interface GuarderiasRepository extends JpaRepository<Guarderias, String> {

    // Metodo para obtener el Gerente asignado a la Guarderia
    @Query("SELECT g.idUsuarioGerente FROM Guarderias g WHERE g.id = ?1")
    Optional<Usuario> findIdUsuarioGerenteById(String id);


    boolean existsByIdOrIdUsuarioGerenteOrRazonSocial(
            String id,
            Usuario idUsuarioGerente,
            String razonSocial);


}
    


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package indiecode.api.siguard.Repositories;

import indiecode.api.siguard.Models.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author zzsglzz
 */
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Optional<Roles>findByRol(String rol);
}

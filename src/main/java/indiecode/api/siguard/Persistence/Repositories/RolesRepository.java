/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package indiecode.api.siguard.Persistence.Repositories;

import indiecode.api.siguard.Persistence.Enums.RolEnum;
import indiecode.api.siguard.Persistence.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author zzsglzz
 */
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Optional<Roles>findByEnumRol(RolEnum enumRol);
}

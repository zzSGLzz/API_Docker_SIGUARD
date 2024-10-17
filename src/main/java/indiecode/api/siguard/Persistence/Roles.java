/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indiecode.api.siguard.Persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import indiecode.api.siguard.Persistence.Enums.RolEnum;
import jakarta.persistence.*;

import java.util.List;
import lombok.Data;

/**
 *
 * @author zzsglzz
 */
@Data
@Entity
public class Roles {
    
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolEnum enumRol;
    @JsonIgnore
    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}

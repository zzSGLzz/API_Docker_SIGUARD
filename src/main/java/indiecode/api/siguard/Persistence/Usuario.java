/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indiecode.api.siguard.Persistence;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zzsglzz
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique=true)
    private String email;
    private String password;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="id_roles")
    private Roles rol;


    //Constructor personalizado
    public Usuario(String nombre, String email, String password, Roles rol ){
        this.nombre=nombre;
        this.email=email;
        this.password=password;
        this.rol=rol;
    }
    
}

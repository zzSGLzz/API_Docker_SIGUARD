/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indiecode.api.siguard.Persistence;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author zzsglzz
 */
@Getter
@Setter
@Entity
public class Guarderias {
    @Id
    private String id;
    
    @Column(name="razon_social")
    private String razonSocial;
    
    @OneToOne(targetEntity=Usuario.class, cascade=CascadeType.PERSIST)
    @JoinColumn(name="id_usuario_gerente")
    private Usuario idUsuarioGerente;


    public Guarderias(String id, String razonSocial, Usuario idUsuarioGerente) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.idUsuarioGerente = idUsuarioGerente;
    }

    public Guarderias() {

    }


    //    @JsonManagedReference
//    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
//    private List<Contrato> contrato;
//    
    
    
}

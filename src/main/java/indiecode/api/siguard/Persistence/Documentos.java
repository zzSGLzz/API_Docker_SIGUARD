/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indiecode.api.siguard.Persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zzsglzz
 */
@Data
@Entity
@NoArgsConstructor
public class Documentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Temporal(TemporalType.DATE)
    private LocalDate inicio;
    @Temporal(TemporalType.DATE)
    private LocalDate fin;
    @ManyToOne
    @JoinColumn(name="id_contrato")
   private Contrato contrato;

    /**
     * Inicializar todos los documentos
     * @param nombre Nombre del Documento
     * @param contrato Contrato asignado del documento
     */
    public Documentos(String nombre,Contrato contrato) {
        this.nombre = nombre;
        this.contrato = contrato;

    }
}

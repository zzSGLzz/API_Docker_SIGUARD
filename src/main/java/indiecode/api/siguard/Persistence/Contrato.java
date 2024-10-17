/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indiecode.api.siguard.Persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zzsglzz
 */
@Data
@Entity
@Table(name="contratos")
@NoArgsConstructor
public class Contrato {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_inicio")
    private LocalDate fechaInicio;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_termino")
    private LocalDate fechaTermino;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_guarderia")
    private Guarderias guarderia;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<Documentos> documentos;

    /**
     * Contructor para inicializar nuevo Contrato
     * @param fechaInicio Fecha del Inicio del Contrato
     * @param fechaTermino Fecha del Termino del Contrato
     * @param guarderia Guarderia asignada al contrato
     */
    public Contrato(LocalDate fechaInicio, LocalDate fechaTermino, Guarderias guarderia) {
        this.fechaInicio =fechaInicio;
        this.fechaTermino =fechaTermino;
        this.guarderia = guarderia;
    }
}

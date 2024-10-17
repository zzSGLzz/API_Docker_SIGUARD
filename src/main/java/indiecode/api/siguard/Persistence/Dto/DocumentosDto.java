/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indiecode.api.siguard.Persistence.Dto;

import indiecode.api.siguard.Persistence.Documentos;

import java.time.LocalDate;

import indiecode.api.siguard.Persistence.Enums.EstadoDocs;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author zzsglzz
 */
@Getter
@Setter
public class DocumentosDto {
    private Long id;
    private String nombre;
    private LocalDate inicio;
    private LocalDate fin;
    private EstadoDocs estado;
    private Long idContrato;
    /**
     * Constructor para inicializar Documentos Dto.
     * @param doc Objeto Documento
     */
    public DocumentosDto(Documentos doc){
        this.id=doc.getId();
        this.nombre=doc.getNombre();
        this.inicio=doc.getInicio();
        this.fin=doc.getFin();
        this.estado=EstadoDocs.calculaEstado(inicio,fin);
        this.idContrato=doc.getContrato().getId();
    }

    public DocumentosDto(String nombre, Long idContrato) {
        this.nombre = nombre;
        this.idContrato = idContrato;
    }
}

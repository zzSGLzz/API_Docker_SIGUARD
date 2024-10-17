/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indiecode.api.siguard.services;

import indiecode.api.siguard.Persistence.Dto.DocumentosDto;
import indiecode.api.siguard.Persistence.Contrato;
import indiecode.api.siguard.Persistence.Documentos;
import indiecode.api.siguard.Persistence.Guarderias;
import indiecode.api.siguard.Persistence.Repositories.ContratoRepository;
import indiecode.api.siguard.Persistence.Repositories.DocumentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author zzsglzz
 */
@Service
public class ContratoService {

    final private String[] anexos = {
            "Descripción de los Servicios Educativos y de Cuidado Infantil",
            "Cronograma de Actividades Diarias",
            "Tarifas y Métodos de Pago",
            "Normas de Seguridad y Salud",
            "Protocolo de Emergencias y Primeros Auxilios",
            "Requisitos y Documentación para la Inscripción de Niños",
            "Políticas de Retiro o Baja del Servicio",
            "Alimentación y Nutrición",
            "Autorización para la Recogida de Niños por Terceros",
            "Personal Autorizado y Calificaciones del Personal",
            "Política de Confidencialidad de Datos Personales",
            "Responsabilidades de los Padres o Tutores"
    };
    final private String[] anexosIntegral = {
            "Descripción de los Servicios Educativos y de Cuidado Infantil",
            "Cronograma de Actividades Diarias",
            "Tarifas y Métodos de Pago",
            "Normas de Seguridad y Salud",
            "Protocolo de Emergencias y Primeros Auxilios",
            "Requisitos y Documentación para la Inscripción de Niños",
            "Políticas de Retiro o Baja del Servicio",
            "Alimentación y Nutrición",
            "Autorización para la Recogida de Niños por Terceros",
            "Personal Autorizado y Calificaciones del Personal",
            "Política de Confidencialidad de Datos Personales",
            "Responsabilidades de los Padres o Tutores"
    };
    @Autowired
    private DocumentosRepository documentosRepository;
    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private DocumentosService documentosService;

    /**
     * Obtiene los documentos de un Contrato
     * @param contrato
     * @return List<DocumentosDto>
     */
    public List<DocumentosDto> getDocsContratos(Contrato contrato){
        Optional<List<Documentos>> allDocumentos= documentosRepository.findAllByContrato(contrato);
        return allDocumentos.map(documentosList -> documentosList.stream()
                .map(DocumentosDto::new).toList()).orElse(null);
    }
    /**
     * Crea contrato junto con sus documentos, después de verificarlos
     * @param guarderia asignada al nuevo contrato
     * @param fechaInicioCot asignada al nuevo contrato
     * @param fechaTerminoCot asignada al nuevo contrato
     */
    public void createContrato(Guarderias guarderia, LocalDate fechaInicioCot, LocalDate fechaTerminoCot)throws RuntimeException{
        validaCreacionContrato(guarderia, fechaInicioCot,fechaTerminoCot);
        Contrato newContrato=new Contrato(fechaInicioCot,fechaTerminoCot,guarderia);
        contratoRepository.save(newContrato);
        documentosService.createAllDocs(newContrato);
    }
    private void validaCreacionContrato(Guarderias guarderias, LocalDate fechaInicioCot, LocalDate fechaTerminoCot)throws RuntimeException{
        if(fechaInicioCot==null || fechaTerminoCot==null){
            throw new RuntimeException("Las fechas no pueden ser nulas");
        }
        if(contratoRepository.existsByGuarderia(guarderias)){
            throw new RuntimeException("Ya existe un contrato asignado a esta guarderia");
        }if(fechaInicioCot.isAfter(fechaTerminoCot)){
            throw new RuntimeException("La fecha de término no puede ser anterior a la fecha de inicio.");
        }
    }


}

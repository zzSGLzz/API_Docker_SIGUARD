package indiecode.api.siguard.services;

import indiecode.api.siguard.Persistence.Contrato;
import indiecode.api.siguard.Persistence.Documentos;
import indiecode.api.siguard.Persistence.Repositories.DocumentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DocumentosService {
    @Autowired
    private DocumentosRepository documentosRepository;
     private final String[] anexos = {
            "Doc_1",
            "Doc_2",
            "Doc_3",
            "Doc_4",
            "Doc_5",
            "Doc_6",
            "Doc_7",
            "Doc_8",
            "Doc_9",
            "Doc_10",
            "Doc_11",
            "Doc_12"
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
    public void createAllDocs(Contrato contratoAsignado) {
        for (int i = 0; i <= 11; i++) {
            Documentos documento = new Documentos(
                    anexos[i],
                    contratoAsignado
            );
            documentosRepository.save(documento);
        }
    }

    public void gerenteUpdateDate(Long idDocumento, LocalDate inicio, LocalDate termino) throws RuntimeException {
      Documentos updateDoc= documentosRepository.findById(idDocumento).orElseThrow(()->new RuntimeException("El Documento no se encuentra"));
        updateDoc.setInicio(inicio);
        updateDoc.setFin(termino);
        documentosRepository.save(updateDoc);
    }
}

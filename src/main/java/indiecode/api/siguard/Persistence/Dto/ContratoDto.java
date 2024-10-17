package indiecode.api.siguard.Persistence.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import indiecode.api.siguard.Persistence.Enums.EstadoDocs;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContratoDto {
    private Long idContrato;
    private String idGuarderia;
    private String nombreGuarderia;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaTermino;
    private EstadoDocs vigencia;
    private List<DocumentosDto> documentos;
    //constructor para mandar contrato
    public ContratoDto(Long idContrato, LocalDate fechaInicio, LocalDate fechaTermino, String nombreGuarderia, List<DocumentosDto> documentos) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.vigencia= EstadoDocs.calculaEstado(fechaInicio, fechaTermino);
        this.nombreGuarderia = nombreGuarderia;
        this.documentos = documentos;
    }
}

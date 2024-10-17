package indiecode.api.siguard.Persistence.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuarderiaContratoDto {
    //Creación Guarderia
    private String idGuarderia;
    private String nombreGuarderia;
    private Long idGerente;
    //Creacion Contrato
    private LocalDate fechaInicioContrato;
    private LocalDate fechaFinContrato;


}

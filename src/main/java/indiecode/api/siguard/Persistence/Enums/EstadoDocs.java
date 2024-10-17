package indiecode.api.siguard.Persistence.Enums;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum EstadoDocs {
    EN_ESPERA,
    NO_PRIORITARIO,
    MEDIANAMENTE_PRIORITARIO,
    PRIORITARIO;
    public static EstadoDocs calculaEstado(LocalDate fechaInicio, LocalDate fechaTermino) {
        if(fechaTermino == null || fechaInicio == null){
            return EN_ESPERA;
        }
        long diasEstado = ChronoUnit.DAYS.between(fechaInicio, fechaTermino);
        if(diasEstado>=63){
            return NO_PRIORITARIO;
        }else if(diasEstado>=21){
            return MEDIANAMENTE_PRIORITARIO;
        }
        return PRIORITARIO;
    }
}

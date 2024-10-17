package indiecode.api.siguard.Persistence.Controllers;

import indiecode.api.siguard.Persistence.Dto.ContratoDto;
import indiecode.api.siguard.Persistence.Contrato;
import indiecode.api.siguard.Persistence.Guarderias;
import indiecode.api.siguard.Persistence.Repositories.ContratoRepository;
import indiecode.api.siguard.Persistence.Repositories.GuarderiasRepository;
import indiecode.api.siguard.services.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("contrato/")
public class ContratoController {
    final private ContratoRepository contratoRepository;
    final private ContratoService contratoService;
    final private GuarderiasRepository guarderiasRepository;
    @Autowired
    public ContratoController(ContratoRepository contratoRepository, ContratoService contratoService, GuarderiasRepository guarderiasRepository) {
        this.contratoRepository = contratoRepository;
        this.contratoService = contratoService;
        this.guarderiasRepository = guarderiasRepository;
    }

    /**
     * Devuelve un contrato
     * @param idGuarderia id de la Guarderia asignada al Contrato
     * @return ResponseEntity
     */
    @GetMapping("{idGuarderia}")
    ResponseEntity<ContratoDto> getAllContratos(@PathVariable String idGuarderia){
        Optional<Guarderias> guarderia=guarderiasRepository.findById(idGuarderia);
        if(guarderia.isPresent()){
            Contrato unContrato= contratoRepository.findContratoByGuarderia(guarderia.get());
            ContratoDto contratoDto= new ContratoDto(
                    unContrato.getId(),
                    unContrato.getFechaInicio(),
                    unContrato.getFechaTermino(),
                    unContrato.getGuarderia().getRazonSocial(),
                    contratoService.getDocsContratos(unContrato)
            );
            return ResponseEntity.ok(contratoDto);
        }
        return ResponseEntity.noContent().build();
    }
    //Agrega fechas Contrato Gerente_solo 1
    //Modifica Contrato solo Admin_muchas veces

}

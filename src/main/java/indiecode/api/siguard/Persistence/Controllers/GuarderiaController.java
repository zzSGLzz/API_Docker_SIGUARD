/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indiecode.api.siguard.Persistence.Controllers;

import indiecode.api.siguard.Persistence.Dto.GuarderiaContratoDto;
import indiecode.api.siguard.Persistence.Dto.GuarderiaDto;
import java.util.List;

import indiecode.api.siguard.Persistence.Repositories.GuarderiasRepository;
import indiecode.api.siguard.services.GuarderiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author zzsglzz
 */
@RestController
@RequestMapping("guarderia/")
public class GuarderiaController {
   private final  GuarderiasRepository guarderiaRepo;
   private final GuarderiaService guarderiaService;


    @Autowired
    public GuarderiaController(GuarderiasRepository guarderiaRepo, GuarderiaService guarderiaService) {
        this.guarderiaRepo = guarderiaRepo;
        this.guarderiaService = guarderiaService;
    }
    //GET
    /**
     * Metodo que devuelve info de la Guarderia Solicitada
     * @param id Identificador de la guarderia
     * @return ResponseEntity con el Dto de la Guarderia
     */
    @GetMapping("{id}")
    ResponseEntity<GuarderiaDto> findGuarderia(@PathVariable String id) {
        return guarderiaRepo.findById(id)
                .map(guarderia -> {
                    GuarderiaDto guarderiaDto = new GuarderiaDto(
                            guarderia.getId(),
                            guarderia.getRazonSocial(),
                            guarderia.getIdUsuarioGerente().getNombre()
                    );
                    return ResponseEntity.ok(guarderiaDto); // Devuelve 200 OK con el GuarderiaDto
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
    /**
     * Metodo que devuelve todas las guarderias
     * @return ResponseEntity con la Lista de dto de las Guarderias
     */
    @GetMapping("all")
    ResponseEntity<List<GuarderiaDto>> getAllGuarderias() {
        try{
            List<GuarderiaDto> todasGuarderiasDto= guarderiaRepo.findAll()
                    .stream()
                    .map(guarderia -> new GuarderiaDto(
                            guarderia.getId(),
                            guarderia.getRazonSocial(),
                            guarderia.getIdUsuarioGerente().getNombre()
                    )).toList();
            return ResponseEntity.ok(todasGuarderiasDto);
        }catch (Exception e){
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("agregar")
    public ResponseEntity<String> creaGuarderiaConContratoYDocumentos(@RequestBody GuarderiaContratoDto guarderiaContratoDto) {
        try{
            String info=guarderiaService.createGuarderia(guarderiaContratoDto);
            return ResponseEntity.ok(info);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: "+e.getMessage());
        }
    }



}

package indiecode.api.siguard.Persistence.Controllers;

import indiecode.api.siguard.Persistence.Dto.DocumentosDto;
import indiecode.api.siguard.Persistence.Repositories.DocumentosRepository;
import indiecode.api.siguard.services.DocumentosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("documento")
public class DocumentoController {
    final private DocumentosRepository documentosRepository;
    private final DocumentosService documentosService;

    @Autowired
    public DocumentoController(DocumentosRepository documentosRepository, DocumentosService documentosService) {
        this.documentosRepository = documentosRepository;
        this.documentosService = documentosService;
    }

    /**
     * Endpoint que devuelve todos los Documentos de la Base de Datos
     * @return List<DocumentosDto>
     */
    @GetMapping("/all")
    ResponseEntity<List<DocumentosDto>> getAllDocumentos() {
        List<DocumentosDto> listaDocumentos= documentosRepository.findAll()
                .stream().map(DocumentosDto::new).toList();
        return ResponseEntity.ok(listaDocumentos);
    }
    /**
     * Endpoint para Gerente, modificación de fechas del documento
     * @param id Documento a modificar
     * @return String resultado
     */ //TODO Error handling fechaTermino>fechaInicio
    @PatchMapping("modifica/{id}")
    @Transactional
    ResponseEntity<String> updateDate(
            @PathVariable Long id,
            @RequestBody DocumentosDto documnetosDto){
        try {
            documentosService.gerenteUpdateDate(id, documnetosDto.getInicio(), documnetosDto.getFin());
        }catch (RuntimeException e){
            ResponseEntity.badRequest().body(e.getMessage());
        }return ResponseEntity.ok("Las Fechas del Documento fueron Actualizadas Correctamente");
    }
}

package indiecode.api.siguard.services;

import indiecode.api.siguard.Persistence.Dto.GuarderiaContratoDto;
import indiecode.api.siguard.Persistence.Guarderias;
import indiecode.api.siguard.Persistence.Usuario;
import indiecode.api.siguard.Persistence.Repositories.GuarderiasRepository;
import indiecode.api.siguard.Persistence.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GuarderiaService {
    private final GuarderiasRepository guarderiasRepository;
    private final UsuarioRepository usuarioRepository;

    private final ContratoService contratoService;
    @Autowired
    public GuarderiaService(GuarderiasRepository guarderiasRepository, UsuarioRepository usuarioRepository, ContratoService contratoService) {
        this.guarderiasRepository = guarderiasRepository;
        this.usuarioRepository = usuarioRepository;
        this.contratoService = contratoService;
    } //Guarderia Convencional - Guarderia Integral

    @Transactional
    public String createGuarderia(GuarderiaContratoDto guarderiaContratoDto) throws RuntimeException {
        //Valida Atributos de Guarderia
        Usuario gerente=validaGuarderia(guarderiaContratoDto);
        //Crea nueva Guarderia solo si los atributos son válidos
            Guarderias newGuarderia= new Guarderias(
                    guarderiaContratoDto.getIdGuarderia(),
                    guarderiaContratoDto.getNombreGuarderia(),
                    gerente);
            guarderiasRepository.save(newGuarderia);
            contratoService.createContrato(
                    newGuarderia,
                    guarderiaContratoDto.getFechaInicioContrato(),
                    guarderiaContratoDto.getFechaFinContrato());
            return "Guarderia, Contrato y Docs creados correctamente";
    }
    /**
     * Metodo que válida los datos para la creaciónd de una guarderia
     * @param guarContrDto Atributos para la creación de Guarderia
     * @return Usuario gerente para la guarderia
     * @throws Exception Error en los atributos
     */
    private Usuario validaGuarderia(GuarderiaContratoDto guarContrDto) throws RuntimeException {
        //Verifica Existencia del Usuario de forma General
        Optional<Usuario> usuarioReques= usuarioRepository.findById(guarContrDto.getIdGerente());
        if (usuarioReques.isEmpty()) {
            throw new RuntimeException("No se encontro el usuario en el Sistema");
        }
        //Verifica si la instancia de la Guarderia ya existe
        if (guarderiasRepository.existsByIdOrIdUsuarioGerenteOrRazonSocial(
               guarContrDto.getIdGuarderia(),
               usuarioReques.get(),
               guarContrDto.getNombreGuarderia()
            )
        ){
            throw new RuntimeException("La guarderia ya existe/Gerente Asignado a otra Guarderia/Nombre Guarderia Duplicado");
        }return usuarioReques.get();
    }
}

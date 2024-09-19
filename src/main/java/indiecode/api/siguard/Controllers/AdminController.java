/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indiecode.api.siguard.Controllers;

import indiecode.api.siguard.Models.Documentos;
import indiecode.api.siguard.Models.Guarderias;
import indiecode.api.siguard.Models.Usuario;
import indiecode.api.siguard.Repositories.DocumentosRepository;
import indiecode.api.siguard.Repositories.GuarderiasRepository;
import indiecode.api.siguard.Repositories.RolesRepository;
import indiecode.api.siguard.Repositories.UsuarioRepository;
import indiecode.api.siguard.dto.RegisterDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zzsglzz
 */
@RestController
@RequestMapping("admin/")
public class AdminController {

//    PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private GuarderiasRepository guarderiaRepo;
    @Autowired
    private DocumentosRepository documentosRepo;
    @Autowired
    private RolesRepository rolRepository;

    //GUARDERIAS
    @RequestMapping("allGuarderias")
    List<Guarderias> getAllGuarderias() {
        return guarderiaRepo.findAll();
    }

    //DOCUMENTOS
    @RequestMapping("allDocuments")
    List<Documentos> getAllDocuments() {
        return documentosRepo.findAll();
    }
    //USUARIOS
    //MOSTRAR
    @RequestMapping("allUsers")
    List<Usuario> getAllusers() {

        return usuarioRepository.findAll();
    }

    //CRUD
    @PostMapping("addAdministrador")
    ResponseEntity<String> addAdmin(@RequestBody RegisterDto registerDto) {
        //Validación de campos TODO
        Usuario nuevoAdmin = new Usuario(
                registerDto.getNombre(),
                registerDto.getEmail(),
                registerDto.getPassword(),
                rolRepository.findByRol("ADMIN").get()
        );
        if (usuarioRepository.existsByEmail(nuevoAdmin.getEmail())||usuarioRepository.existsByNombre(nuevoAdmin.getNombre())) {
            // Si el usuario ya existe, no lo guardes
            System.out.println("El usuario ya existe");
            return new ResponseEntity<>("El usuario ya existe, verifique los datos", HttpStatus.CONFLICT);
        } else {
            // Si no existe, guárdalo
            usuarioRepository.save(nuevoAdmin);
            return new ResponseEntity<>("User register success", HttpStatus.OK);
        }
    }
    @PostMapping("addGerente")
    ResponseEntity<String> addGerente(@RequestBody RegisterDto registerDto) {
        //Validación de campos TODO
        Usuario nuevoGerente = new Usuario(
                registerDto.getNombre(),
                registerDto.getEmail(),
                registerDto.getPassword(),
                rolRepository.findByRol("GERENTE").get()
        );
        if (usuarioRepository.existsByEmail(nuevoGerente.getEmail())||usuarioRepository.existsByNombre(nuevoGerente.getNombre())) {
            // Si el usuario ya existe, no lo guardes
            System.out.println("El usuario ya existe");
            return new ResponseEntity<>("El usuario ya existe, verifique los datos", HttpStatus.CONFLICT);
        } else {
            // Si no existe, guárdalo
            usuarioRepository.save(nuevoGerente);
            return new ResponseEntity<>("User register success", HttpStatus.OK);
        }
    }
//    @RequestMapping("addGerente")
//    @RequestMapping("addGuarderia")

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package indiecode.api.siguard.Persistence.Repositories;

import indiecode.api.siguard.Persistence.Contrato;
import indiecode.api.siguard.Persistence.Documentos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author zzsglzz
 */
public interface DocumentosRepository extends JpaRepository<Documentos, Long> {

    Optional<List<Documentos>> findAllByContrato(Contrato contrato);
}

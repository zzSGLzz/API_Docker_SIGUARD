package indiecode.api.siguard.Persistence.Repositories;

import indiecode.api.siguard.Persistence.Contrato;
import indiecode.api.siguard.Persistence.Guarderias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
    Contrato findContratoByGuarderia(Guarderias guarderias);
    Optional<Contrato> findContratoById(Long id);

    Boolean existsByGuarderia(Guarderias guarderias);
}

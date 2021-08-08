package br.com.publicacoesonline.mspro.repositories;

import br.com.publicacoesonline.mspro.models.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    Optional<Processo> findByNumero(String numero);
}

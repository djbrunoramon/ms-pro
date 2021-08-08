package br.com.publicacoesonline.mspro.repositories;

import br.com.publicacoesonline.mspro.models.Reu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReuRepository extends JpaRepository<Reu, Long> {
}

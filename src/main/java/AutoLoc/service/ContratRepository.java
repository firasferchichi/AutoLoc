package AutoLoc.service;

import AutoLoc.domain.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

interface ContratRepository extends JpaRepository<Contrat, Long> {
}

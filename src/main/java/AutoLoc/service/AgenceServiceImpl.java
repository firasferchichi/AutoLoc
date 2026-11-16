package AutoLoc.service;

import AutoLoc.domain.Agence;
import AutoLoc.domain.Vehicle;
import AutoLoc.repository.AgenceRepository;
import AutoLoc.repository.VehiculeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;
    private static final Logger log = LoggerFactory.getLogger(AgenceServiceImpl.class);

    // Question 2 : Délai de 2 minutes (120000 ms) après la fin de chaque exécution
    @Scheduled(fixedDelay = 120000)
    public void suivreNombreAgences() {
        List<Agence> agences = agenceRepository.findAll();

        log.info("=== SUIVI DES AGENCES ===");
        log.info("Nombre total d'agences enregistrées : {}", agences.size());
        log.info("Date et heure de la vérification : {}", LocalDateTime.now());
        log.info("=========================");
    }

    @Override
    public List<Agence> getAllAgences() { return agenceRepository.findAll(); }

    @Override
    public Agence addAgence(Agence agence) { return agenceRepository.save(agence); }

    @Override
    public Agence updateAgence(Long id, Agence agence) {
        Agence existing = agenceRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setNom(agence.getNom());
            existing.setVille(agence.getVille());
            existing.setAdresse(agence.getAdresse());
            existing.setTelephone(agence.getTelephone());
            return agenceRepository.save(existing);
        }
        return null;
    }

    @Override
    public Agence getAgenceById(Long id) { return agenceRepository.findById(id).orElse(null); }

    @Override
    public void deleteAgence(Long id) { agenceRepository.deleteById(id); }

    // --- SCÉNARIO 2 : Ajout agence + véhicules ---
    @Override
    @Transactional
    public Agence ajouterAgenceEtAffecterVehicules(Agence agence) {
        Agence savedAgence = agenceRepository.save(agence);
        if (agence.getVehicules() != null) {
            for (Vehicle v : agence.getVehicules()) {
                v.setAgence(savedAgence); // Affectation côté propriétaire (Vehicule)
                vehiculeRepository.save(v);
            }
        }
        return savedAgence;
    }
}
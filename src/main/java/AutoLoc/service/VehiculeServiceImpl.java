package AutoLoc.service;

import AutoLoc.domain.Equipement;
import AutoLoc.domain.Vehicle;
import AutoLoc.repository.EquipementRepository;
import AutoLoc.repository.VehiculeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private static final Logger log = LoggerFactory.getLogger(VehiculeServiceImpl.class);

    // Question 1 : Délai de 5s au démarrage, puis toutes les 60s (60000 ms)
    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    public void actualiserEtatVehicules() {
        List<Vehicle> vehicules = vehiculeRepository.findAll();

        log.info("=== ACTUALISATION DES VÉHICULES ===");
        log.info("Nombre total de véhicules enregistrés : {}", vehicules.size());
        log.info("Date et heure de l'actualisation : {}", LocalDateTime.now());
        log.info("=====================================");
    }

    // Question 3 : Tous les lundis à 09h00 du matin (Cron: Sec Min Heure Jour Mois JourSemaine)
    @Scheduled(cron = "0 0 9 * * MON")
    public void genererRapportVehicules() {
        List<Vehicle> vehicules = vehiculeRepository.findAll();

        log.info("=== RAPPORT HEBDOMADAIRE DES VÉHICULES ===");
        log.info("Date et heure de génération : {}", LocalDateTime.now());
        log.info("Nombre total de véhicules dans le parc : {}", vehicules.size());
        log.info("===========================================");
    }

    @Override
    public List<Vehicle> afficherAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicle ajouterVehicule(Vehicle vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicle modifierVehicule(Long id, Vehicle vehicule) {
        Vehicle existing = vehiculeRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setImmatriculation(vehicule.getImmatriculation());
            existing.setMarque(vehicule.getMarque());
            existing.setModele(vehicule.getModele());
            existing.setCategorie(vehicule.getCategorie());
            existing.setTarifJournalier(vehicule.getTarifJournalier());
            existing.setStatut(vehicule.getStatut());
            return vehiculeRepository.save(existing);
        }
        return null;
    }

    @Override
    public Vehicle afficherVehiculeById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    private final EquipementRepository equipementRepository;

    @Override
    @Transactional

    public Vehicle affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicle v = vehiculeRepository.findById(idVehicule).orElse(null);
        Equipement eq = equipementRepository.findById(idEquipement).orElse(null);
        if (v != null && eq != null) {
            v.getEquipements().add(eq); // On ajoute l'équipement
            return vehiculeRepository.save(v);
        }
        return null;
    }

    @Override
    @Transactional
    public Vehicle desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicle v = vehiculeRepository.findById(idVehicule).orElse(null);
        Equipement eq = equipementRepository.findById(idEquipement).orElse(null);
        if (v != null && eq != null) {
            v.getEquipements().remove(eq); // On retire l'équipement
            return vehiculeRepository.save(v);
        }
        return null;
    }
}
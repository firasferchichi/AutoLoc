package AutoLoc.service;

import AutoLoc.domain.Equipement;
import AutoLoc.domain.Vehicle;
import AutoLoc.repository.EquipementRepository;
import AutoLoc.repository.VehiculeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;

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
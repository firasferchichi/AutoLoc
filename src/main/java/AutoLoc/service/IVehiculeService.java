package AutoLoc.service;

import AutoLoc.domain.Vehicle;
import java.util.List;

public interface IVehiculeService {
    List<Vehicle> afficherAllVehicules();
    Vehicle ajouterVehicule(Vehicle vehicule);
    Vehicle modifierVehicule(Long id, Vehicle vehicule);
    Vehicle afficherVehiculeById(Long id);
    void supprimerVehicule(Long id);
    Vehicle affecterEquipement(Long idVehicule, Long idEquipement);
    Vehicle desaffecterEquipement(Long idVehicule, Long idEquipement);
}
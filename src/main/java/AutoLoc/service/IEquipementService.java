package AutoLoc.service;

import AutoLoc.domain.Equipement;
import java.util.List;

public interface IEquipementService {
    List<Equipement> getAllEquipements();
    Equipement addEquipement(Equipement equipement);
    Equipement getEquipementById(Long id);
    void deleteEquipement(Long id);
}
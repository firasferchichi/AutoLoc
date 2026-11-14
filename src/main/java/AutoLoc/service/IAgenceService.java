package AutoLoc.service;

import AutoLoc.domain.Agence;
import java.util.List;

public interface IAgenceService {
    List<Agence> getAllAgences();
    Agence addAgence(Agence agence);
    Agence updateAgence(Long id, Agence agence);
    Agence getAgenceById(Long id);
    void deleteAgence(Long id);

    // Méthode pour le Scénario 2
    Agence ajouterAgenceEtAffecterVehicules(Agence agence);
}
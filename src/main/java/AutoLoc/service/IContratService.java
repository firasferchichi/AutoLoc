package AutoLoc.service;

import AutoLoc.domain.Client;
import AutoLoc.domain.Contrat;

import java.util.List;

public interface IContratService {
    Contrat ajouterContrat(Contrat contrat);
    Contrat modifierContrat(Contrat contrat);
    Contrat afficherContrat(Long id);
    List<Contrat> listerContrats();
    void deleteContrat(Long id);
}

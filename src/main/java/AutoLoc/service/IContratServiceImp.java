package AutoLoc.service;

import AutoLoc.domain.Contrat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IContratServiceImp implements IContratService  {
    private final ContratRepository contratRepository;

    @Override
    public Contrat ajouterContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat modifierContrat(Contrat contrat) {
        return contratRepository.save(contrat);    }

    @Override
    public Contrat afficherContrat(Long id) {
        return contratRepository.findById(id).orElse(null);
    }


    @Override
    public List<Contrat> listerContrats() {
        return List.of();
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);

    }
}

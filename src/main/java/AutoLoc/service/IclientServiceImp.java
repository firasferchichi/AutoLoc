package AutoLoc.service;

import AutoLoc.domain.Client;
import AutoLoc.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class IclientServiceImp implements IClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client ajoutClient(Client Client) {
        return null;
    }

    @Override
    public Client modifierClient(Client Client) {
        return clientRepository.save(Client);
    }

    @Override
    public Client afficherClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> afficherAllClients() {
        return List.of();
    }

    @Override
    public void removeClient(Long id) {
         clientRepository.deleteById(id);
    }

}

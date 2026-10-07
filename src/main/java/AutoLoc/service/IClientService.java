package AutoLoc.service;

import AutoLoc.domain.Client;

import java.util.List;

public interface IClientService {
    Client ajoutClient(Client Client);
    Client modifierClient(Client Client);
    Client afficherClientById(Long id);
    List<Client> afficherAllClients();
    void removeClient(Long id);

}

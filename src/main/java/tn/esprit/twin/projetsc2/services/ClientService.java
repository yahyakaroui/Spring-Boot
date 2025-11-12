package tn.esprit.twin.projetsc2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.twin.projetsc2.entities.Client;
import tn.esprit.twin.projetsc2.repository.ClientRepo;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements ClientInterface {
    @Autowired
    private  ClientRepo clientRepo;

    @Override
    public List<Client> retrieveAllClients() {
        return clientRepo.findAll();
    }

    @Override
    public Client retrieveClient(Long idClient) {
        return clientRepo.findById(idClient).orElse(null);
    }

    @Override
    public Client addClient(Client c) {
        return clientRepo.save(c);
    }

    @Override
    public Client updateClient(Long idClient, Client c) {
        // ⚠️ On ne change pas l’ID manuellement (Hibernate gère ça)
        // On récupère l'ancien client pour le mettre à jour
        Client existing = clientRepo.findById(idClient).orElse(null);
        if (existing == null) return null;

        existing.setIdentifiant(c.getIdentifiant());
        existing.setDatePremiereVisite(c.getDatePremiereVisite());
        return clientRepo.save(existing);
    }

    @Override
    public void removeClient(Long idClient) {
        clientRepo.deleteById(idClient);
    }

    @Override
    public List<Client> addClients(List<Client> clients) {
        return clientRepo.saveAll(clients);
    }

    @Override
    public List<Client> searchByIdentifiant(String part) {
        return clientRepo.findByIdentifiantContainingIgnoreCase(part);
    }

    @Override
    public List<Client> findByDateRange(Date from, Date to) {
        return clientRepo.findByDatePremiereVisiteBetween(from, to);
    }

    @Override
    public List<Client> withMinOrders(int min) {
        return clientRepo.findClientsWithMinOrders(min);
    }
}

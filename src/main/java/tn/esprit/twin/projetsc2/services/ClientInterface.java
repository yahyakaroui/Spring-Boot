package tn.esprit.twin.projetsc2.services;

import tn.esprit.twin.projetsc2.entities.Client;

import java.util.Date;
import java.util.List;

public interface ClientInterface {
    List<Client> retrieveAllClients();
    Client retrieveClient(Long idClient);
    Client addClient(Client c);
    Client updateClient(Long idClient, Client c);
    void removeClient(Long idClient);
    List<Client> addClients(List<Client> clients);

    // keyword / jpql
    List<Client> searchByIdentifiant(String part);
    List<Client> findByDateRange(Date from, Date to);
    List<Client> withMinOrders(int min);
}

package tn.esprit.twin.projetsc2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.twin.projetsc2.entities.Client;

import java.util.Date;
import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

    // Recherche par identifiant (keyword)
    List<Client> findByIdentifiantContainingIgnoreCase(String part);

    // Recherche par période (keyword)
    List<Client> findByDatePremiereVisiteBetween(Date from, Date to);

    // JPQL : clients avec au moins N commandes
    @Query("SELECT c FROM Client c WHERE size(c.commandes) >= :min")
    List<Client> findClientsWithMinOrders(int min);
}

package tn.esprit.twin.projetsc2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.twin.projetsc2.entities.Commande;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandeRepo extends JpaRepository<Commande, Long> {

    // Keyword
    List<Commande> findByTotalCommandeGreaterThan(Float montant);

    // Keyword (recherche par date)
    List<Commande> findByDateCommandeBetween(LocalDate from, LocalDate to);

    // JPQL : commandes avec remise supérieure à une valeur
    @Query("SELECT c FROM Commande c WHERE c.totalRemise > :min")
    List<Commande> findCommandesWithRemiseAbove(Float min);
}
